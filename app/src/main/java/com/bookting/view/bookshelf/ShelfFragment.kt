package com.bookting.view.bookshelf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.data.SHELF
import com.bookting.databinding.ShelfFragmentBinding
import com.bookting.databinding.ShelfPagerItemBinding
import com.bookting.ui.GridBook
import com.bookting.utils.getCurrentTime
import com.bookting.utils.getCurrentTimeBefore
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class ShelfFragment : BaseFragment<ShelfFragmentBinding>(R.layout.shelf_fragment) {

    override fun ShelfFragmentBinding.initView() {

    }

    override fun onResume() {
        super.onResume()

        initView()

        viewModel.getShelfByUser(getCurrentTime().substring(0, 6))
        viewModel.shelfResponse.observe(this@ShelfFragment) {
            if (it.result == MainConstants.SUCCESS) {
                it.data?.let { data ->
                    binding.tvTitle.text =
                        getString(R.string.shelf_title_by_user, sharedHelper.getUserNick)
                    binding.tvCount.text = getString(R.string.shelf_count, it.total_count)
                    drawGrid(data)
                }
            } else {

            }

        }
    }

    fun drawGrid(arr: List<SHELF.UserShelfData>) = with(binding) {
        val adapter = ViewPagerAdapter()
        adapter.addItems(arr)
        viewPager.adapter = adapter
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

    inner class ViewPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        var items = ArrayList<SHELF.UserShelfData>()

        fun addItems(arr: List<SHELF.UserShelfData>){
            items.addAll(arr)
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder as PagerHolder
            holder.bind(items)
        }

        override fun getItemCount() = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val binding = ShelfPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PagerHolder(binding)
        }

        inner class PagerHolder(val binding: ShelfPagerItemBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(item: List<SHELF.UserShelfData>) = with(binding){
                val gridArr = ArrayList<GridBook>()
                item.forEach {
                    val grid = GridBook(root.context)
                    grid.apply {
                        setTitle(it.book_name)
                        setBooksImg(it.image_url)
                        setGrade(it.rate)
                    }
                    gridArr.add(grid)
                }
                gridArr.forEach {
                    flexLayout.addView(it)
                }
            }
        }

    }
}