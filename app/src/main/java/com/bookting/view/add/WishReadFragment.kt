package com.bookting.view.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bookting.BaseActivity
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.data.WishBookData
import com.bookting.databinding.FragmentHomeBinding
import com.bookting.databinding.WishReadFragmentBinding
import com.bookting.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WishReadFragment(val bookId: Int, val viewModel: MainViewModel) :
    BottomSheetDialogFragment() {
    lateinit var fragBinding: WishReadFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragBinding = WishReadFragmentBinding.inflate(inflater, container, false)
        return fragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragBinding.fragment = this@WishReadFragment
    }

    fun addBook() = with(fragBinding) {
        viewModel.addWishBook(WishBookData(bookId, fragBinding.etMemo.getMemo()))

        viewModel.addWishResponse.observe(requireActivity()) {
            val title =
                if (it.result == MainConstants.SUCCESS) getString(R.string.success) else getString(R.string.fail)
            val msg =
                if (it.result == MainConstants.SUCCESS) getString(R.string.success_add_msg) else getString(
                    R.string.add_book_err_msg
                )
            (requireActivity() as BaseActivity<*>).showBtnOneDlg(
                title,
                msg,
                getString(R.string.ok)
            ) { this@WishReadFragment.dismiss() }
        }
    }

}