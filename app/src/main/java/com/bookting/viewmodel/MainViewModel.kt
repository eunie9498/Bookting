package com.bookting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookting.data.Users

class MainViewModel : ViewModel() {
    val user = MutableLiveData<Users>()

    fun getPw(email: String, pw: String) {
        user.value = Users(email, pw)
    }

}