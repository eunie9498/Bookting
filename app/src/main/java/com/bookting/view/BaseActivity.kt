package com.bookting.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : AppCompatActivity() {

    lateinit var binding: T
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


    abstract fun initView()

    //TODO alert, toast, dialog, moveTo ...
}