package com.bookting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookting.data.UserDataResponse
import com.bookting.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewModel(val repository: MainRepository) : ViewModel() {
    val userDataResponse: LiveData<UserDataResponse>
        get() = _userDataResponse
    private val _userDataResponse = MutableLiveData<UserDataResponse>()

    fun getUserData() {
        repository.run {
            getUserData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _userDataResponse.postValue(it)
                }
        }
    }
}