package com.bookting.repository

import android.content.Context
import android.util.Log
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.api.BookAPI
import com.bookting.data.*
import com.bookting.di.BookComponent
import com.bookting.di.BookNetworkModule
import com.bookting.di.DaggerBookComponent
import com.bookting.utils.showToast
import com.bookting.viewmodel.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Header
import java.io.IOException
import javax.inject.Inject

class MainRepository(context: Context) {
    var component: BookComponent = DaggerBookComponent.builder()
        .bookNetworkModule(BookNetworkModule(context))
        .build()

    @Inject
    lateinit var api: BookAPI

    @Inject
    lateinit var sharedHelper: SharedHelper

    init {
        component.inject(this)
    }

    fun join(context: Context, body: JoinBody) {
        api.run {
            joinUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.result == MainConstants.SUCCESS) {
                        showToast(context, "회원가입 완료")
                    } else {
                        Log.d("체크해보자", it.reason ?: "")
                    }
                }
        }
    }

    fun home(header: Map<String, String>) = api.getHomeByUser(header)

    fun login(body: LoginBody) = api.login(body)

    fun bestSeller() = api.getBestSeller()

    fun newBooks() = api.getNewBook()

}