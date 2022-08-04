package com.bookting.view.add

import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.AlreadyBookItem
import com.bookting.data.MainConstants
import com.bookting.data.TagItem
import com.bookting.databinding.ActivityAlreadyReadBinding

class AlreadyReadActivity :
    BaseActivity<ActivityAlreadyReadBinding>(R.layout.activity_already_read),
    AlreadyAdapter.AlreadyListener {

    var bookId = 0

    override fun ActivityAlreadyReadBinding.onCreate() = with(binding) {

        intent.extras?.let { extra ->
            if (extra.containsKey(MainConstants.BUNDLE_KEY.BOOK_ID)) {
                bookId = extra.getInt(MainConstants.BUNDLE_KEY.BOOK_ID)
            }
        }

        binding.activity = this@AlreadyReadActivity
        recyclerView.adapter = AlreadyAdapter(this@AlreadyReadActivity)
        viewModel.tag()
        viewModel.tagData.observe(this@AlreadyReadActivity) {
            (recyclerView.adapter as AlreadyAdapter).addItem(it.toMutableList())
        }


    }

    override fun clickTag(item: TagItem) {
        viewModel.tagData.value!!.forEach {
            if (item == it) {
                it.selected = item.selected
            }
        }
    }

    fun addBook() = with(binding) {
        val ids = ArrayList<Int>()
        viewModel.tagData.value!!.filter { it.selected == true }.forEach {
            ids.add(it.id)
        }

        viewModel.addAlreadyBook(
            AlreadyBookItem(
                bookId,
                etMemo.getMemo(),
                ratingBar.rating.toInt(),
                ids
            )
        )

        viewModel.addAlreadyResponse.observe(this@AlreadyReadActivity) {
            val title =
                if (it.result == MainConstants.SUCCESS) getString(R.string.success) else getString(R.string.fail)
            val msg =
                if (it.result == MainConstants.SUCCESS) getString(R.string.success_add_msg) else getString(
                    R.string.add_book_err_msg
                )
            showBtnOneDlg(
                title,
                msg,
                getString(R.string.ok)
            )
        }
    }
}