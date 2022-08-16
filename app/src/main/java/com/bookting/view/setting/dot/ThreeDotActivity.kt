package com.bookting.view.setting.dot

import android.os.Bundle
import androidx.databinding.BindingAdapter
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityThreeDotBinding
import com.bookting.ui.LineEndTxt

class ThreeDotActivity : BaseActivity<ActivityThreeDotBinding>(R.layout.activity_three_dot) {
    var version = ""
    override fun ActivityThreeDotBinding.onCreate() {
        binding.activity = this@ThreeDotActivity
        binding.version = "1.0.0"
    }

    fun moveToPage(type: Int) {
        when (type) {
            0 -> {

            }
            1 -> {

            }
            2 -> {

            }
            3 -> {

            }
            4 -> {

            }
            5 -> {

            }
            6 -> {

            }
        }
    }
}