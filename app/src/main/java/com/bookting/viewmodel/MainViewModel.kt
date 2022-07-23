package com.bookting.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookting.data.*
import com.bookting.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(val repository: MainRepository) : ViewModel() {

    val loginResponse = MutableLiveData<LoginResponse>()

    private val _homeResponse = MutableLiveData<HomeResponse>()

    val homeResponse: LiveData<HomeResponse>
        get() = _homeResponse

    private val _bookData = MutableLiveData<List<GetBookData>>()

    val bookData: LiveData<List<GetBookData>>
        get() = _bookData

    fun getHome() = repository.run {
        val BooktingHeader = mutableMapOf<String,String>()
        BooktingHeader["access_token"] = "Bearer " + sharedHelper.getRefreshToken
        home(BooktingHeader.toMap()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.result == MainConstants.SUCCESS) {
                    _homeResponse.postValue(it)
                } else {
                    if(it.reason!!.contains("access_token")){
                        login(LoginBody(
                            email = "eunie@chaeking.co.kr",
                            sharedHelper.newEncrypt("qqqq1111".toByteArray()),
                            sharedHelper.getFbToken.substring(0, 32)
                        ))
                    }
                    Log.d("체크해보자", it.reason!!)
                }
            }
    }

    fun join(context: Context, body: JoinBody) = repository.join(context, body)

    fun login(body: LoginBody) {
        repository.run {
            login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loginResponse.postValue(it)
                    if (it.result == MainConstants.SUCCESS) {
                        it.data?.let { data ->
                            repository.sharedHelper.addPreference(
                                MainConstants.Shared.REFRESH_TOKEN,
                                data.refresh_token
                            )
                            repository.sharedHelper.addPreference(
                                MainConstants.Shared.ACCESS_TOKEN,
                                data.access_token
                            )
                            loginResponse.postValue(it)
                        }
                    }
                }
        }
    }
}