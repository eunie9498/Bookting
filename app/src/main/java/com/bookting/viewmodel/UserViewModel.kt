package com.bookting.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookting.data.*
import com.bookting.repository.MainRepository
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewModel(val repository: MainRepository) : ViewModel() {

    val joinResponse: MutableLiveData<ResultResponse>
        get() = _joinResponse
    private val _joinResponse = MutableLiveData<ResultResponse>()

    val loginResponse = MutableLiveData<LoginResponse>()

    val userDataResponse: LiveData<UserDataResponse>
        get() = _userDataResponse
    private val _userDataResponse = MutableLiveData<UserDataResponse>()

    val BooktingHeader = mutableMapOf<String, String>()

    val alreadyDataResponse: MutableLiveData<GetAlreadyBookResponse>
        get() = _alreadyDataResponse

    private val _alreadyDataResponse = MutableLiveData<GetAlreadyBookResponse>()

    fun GetAlreadyRead(month:String, page: Int, size: Int) {
        BooktingHeader["access_token"] = "Bearer " + repository.sharedHelper.getAccessToken
        repository.run {
            GetAlreadyRead(BooktingHeader.toMap(), month, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    alreadyDataResponse.postValue(it)
                }, {
                    alreadyDataResponse.postValue(
                        GetAlreadyBookResponse(
                            MainConstants.FAIL,
                            it.message ?: "",
                            null
                        )
                    )
                })
        }
    }

    fun getUserData() {
        BooktingHeader["access_token"] = "Bearer " + repository.sharedHelper.getAccessToken

        repository.run {
            getUserData(BooktingHeader.toMap()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _userDataResponse.postValue(it) }, {
                    _userDataResponse.postValue(
                        UserDataResponse(
                            MainConstants.FAIL,
                            "access_token",
                            null
                        )
                    )
                })
        }
    }

    fun join(body: JoinBody) {
        repository.run {
            join(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _joinResponse.postValue(it)
                }
        }
    }

    fun login(body: LoginBody) {
        repository.run {
            login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
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
                }, {
                    loginResponse.postValue(
                        LoginResponse(
                            MainConstants.FAIL,
                            it.message ?: "token",
                            null
                        )
                    )
                })
        }
    }
}