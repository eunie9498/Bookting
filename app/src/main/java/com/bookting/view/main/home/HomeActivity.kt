package com.bookting.view.main.home

import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityHomeBinding

class HomeActivity: BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    override fun ActivityHomeBinding.onCreate() {
        binding.badge.setEmoji("ð")

    }
}