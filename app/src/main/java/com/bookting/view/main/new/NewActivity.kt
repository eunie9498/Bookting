package com.bookting.view.main.new

import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.NewBookActivityBinding

class NewActivity : BaseActivity<NewBookActivityBinding>(R.layout.new_book_activity),
    NewAdapter.NewListener {

    override fun NewBookActivityBinding.onCreate() {
        binding.appBar.binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.newBooks()
        viewModel.bookData.observe(this@NewActivity) {
            recyclerView.adapter = NewAdapter(this@NewActivity)
            (recyclerView.adapter as NewAdapter).addItem(it)
        }
    }

    override fun onDetail(bookId: Int) {
        moveToDetail(bookId)
    }
}