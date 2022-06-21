package com.bookting

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
        val factory = ViewModelFactory(MainRepository())
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        repository = MainRepository()

        binding.onCreate()
    }

    inner class ViewModelFactory(val repository: MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(MainRepository::class.java).newInstance(repository)
        }
    }

    open fun T.onCreate() = Unit

}