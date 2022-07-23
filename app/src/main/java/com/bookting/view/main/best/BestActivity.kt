package com.bookting.view.main.best

import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityBestSellerBinding

class BestActivity : BaseActivity<ActivityBestSellerBinding>(R.layout.activity_best_seller) {

    override fun ActivityBestSellerBinding.onCreate() {
        binding.appBar.binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        viewModel.bestSeller()
        viewModel.bookData.observe(this@BestActivity) {
            recyclerView.adapter = BestAdapter()
            (recyclerView.adapter as BestAdapter).addItem(it)
        }
    }
}