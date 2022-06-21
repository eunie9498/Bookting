package com.bookting.repository

import com.bookting.api.BookAPI
import com.bookting.data.JoinBody
import com.bookting.data.MainConstants
import com.bookting.data.Users
import com.bookting.viewmodel.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainRepository {
    @Inject
    lateinit var api: BookAPI

    fun join(body: JoinBody): Disposable {
        return api.run {
            joinUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.result == MainConstants.SUCCESS) {

                    }
                }
        }
    }
}