package com.bookting

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bookting.repository.MainRepository
import com.bookting.view.main.NetworkActivity
import com.bookting.viewmodel.MainViewModel

open class BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : NetworkActivity() {

    lateinit var binding: T
    lateinit var viewModel: MainViewModel
    lateinit var repository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        viewModel = MainViewModel()
        repository = MainRepository()

        binding.onCreate()
    }

    open fun T.onCreate() = Unit

}