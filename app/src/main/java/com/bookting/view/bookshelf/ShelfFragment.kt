package com.bookting.view.bookshelf

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.databinding.ShelfFragmentBinding
import com.bookting.utils.getCurrentTime
import com.bookting.utils.getCurrentTimeBefore

class ShelfFragment : BaseFragment<ShelfFragmentBinding>(R.layout.shelf_fragment) {

    override fun ShelfFragmentBinding.initView() {

    }

    override fun onResume() {
        super.onResume()
        viewModel.getShelfByUser(getCurrentTime().substring(0, 6))
        viewModel.shelfResponse.observe(this@ShelfFragment) {
            if (it.result == MainConstants.SUCCESS) {
                it.data?.let { data ->
                    binding.tvTitle.text =
                        getString(R.string.shelf_title_by_user, sharedHelper.getUserNick)
                    binding.tvCount.text = getString(R.string.shelf_count, it.total_count)
                }

                initView()
            } else {

            }

        }
    }


    fun initView() = with(binding) {
        val arr = getCurrentTimeBefore(6)
        var spinnerAdapter =
            ArrayAdapter(root.context, R.layout.spinner_txt, arr)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                //arr[pos]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
    }
}