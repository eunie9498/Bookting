package com.bookting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookting.data.Users
import com.bookting.repository.MainRepository

class MainViewModel(val repository: MainRepository) : ViewModel() {

    val user = MutableLiveData<Users>()

    val _user: MutableLiveData<Users>
        get() = user

    fun getPw(email: String, pw: String) {
        user.value = Users(email, pw)
    }

}