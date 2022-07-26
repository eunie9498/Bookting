package com.bookting.view.detail

import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityBookDetailBinding
import com.bookting.utils.setRoundImg

class DetailActivity : BaseActivity<ActivityBookDetailBinding>(R.layout.activity_book_detail) {
    var book_id = 0

    override fun ActivityBookDetailBinding.onCreate() {
        intent.extras?.let { extra ->
            if (extra.containsKey(MainConstants.BUNDLE_KEY.BOOK_ID)) {
                book_id = extra.getInt(MainConstants.BUNDLE_KEY.BOOK_ID)
            }
        }

        initView()
    }

    fun initView() = with(binding) {
        appBar.appBarBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.getBookDetail(book_id)
        viewModel.detailData.observe(this@DetailActivity) {
            binding.data = it
            binding.bookImg.setRoundImg(it.image_url)
            binding.author =
                if (it.authors.isNotEmpty()) it.authors.joinToString { it } else getString(R.string.no_info)
        }
    }
}