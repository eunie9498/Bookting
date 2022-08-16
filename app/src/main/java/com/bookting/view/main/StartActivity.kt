package com.bookting.view.main


import android.content.Intent
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityStartBinding
import com.bookting.view.start.JoinActivity
import com.bookting.view.start.LoginActivity
open class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start) {

    override fun ActivityStartBinding.onCreate() {
        binding.activity = this@StartActivity
    }

    fun goJoin() {
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }

    fun goLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}