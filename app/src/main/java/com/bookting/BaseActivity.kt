package com.bookting

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bookting.api.BookAPI
import com.bookting.di.BookComponent
import com.bookting.main.MainActivity
import com.bookting.main.NetworkActivity
import javax.inject.Inject

open class BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : NetworkActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.onCreate()

    }

    open fun T.onCreate() = Unit

}