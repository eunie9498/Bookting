package com.bookting

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bookting.data.SharedHelper
import com.bookting.main.NetworkActivity

open class BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : NetworkActivity() {

    lateinit var binding: T
    var sharedHelper = SharedHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.onCreate()

    }

    open fun T.onCreate() = Unit

}