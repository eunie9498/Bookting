package com.bookting.view

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bookting.api.BookAPI
import com.bookting.main.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : MainActivity() {

    @Inject
    lateinit var api: BookAPI

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

    fun getFbToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("FCM ERR", task.exception.toString())
                return@OnCompleteListener
            }
            val token = task.result
            //todo token save
        }
        )
    }

    abstract fun initView()

    //TODO alert, toast, dialog, moveTo ...
}