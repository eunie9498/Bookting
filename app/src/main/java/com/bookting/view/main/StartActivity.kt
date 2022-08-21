package com.bookting.view.main


import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityStartBinding
import com.bookting.utils.dpToPx
import com.bookting.view.start.JoinActivity
import com.bookting.view.start.LoginActivity

open class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start) {

    override fun ActivityStartBinding.onCreate() {
        binding.activity = this@StartActivity

        val params = RelativeLayout.LayoutParams(
            dpToPx(this@StartActivity.resources, 300f).toInt(),
            dpToPx(this@StartActivity.resources, 300f).toInt()
        )
        lottieImg.layoutParams = params
        lottieImg.playAnimation()

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