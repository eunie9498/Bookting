package com.bookting.view.main.new

import android.util.Log
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.NewBookActivityBinding
import com.google.gson.Gson

class NewActivity: BaseActivity<NewBookActivityBinding>(R.layout.new_book_activity) {

    override fun NewBookActivityBinding.onCreate() {
        viewModel.newBooks()
        viewModel.bookData.observe(this@NewActivity) {
            recyclerView.adapter = NewAdapter()
            (recyclerView.adapter as NewAdapter).addItem(it)
            Log.d("체크해보자", Gson().toJson(it))
        }
    }
}