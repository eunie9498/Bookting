package com.bookting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookting.models.Users

class MainViewModel : ViewModel() {
    val user = MutableLiveData<Users>()

    fun getPw(email: String, pw: String) {
        user.value = Users(email, pw)
    }
}