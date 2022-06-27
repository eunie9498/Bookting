package com.bookting.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookting.data.JoinBody
import com.bookting.data.LoginBody
import com.bookting.data.Users
import com.bookting.repository.MainRepository

class MainViewModel(val repository: MainRepository) : ViewModel() {


    val user = MutableLiveData<Users>()

    val _user: MutableLiveData<Users>
        get() = user

    fun getPw(email: String, pw: String) {
        user.value = Users(email, pw)
    }

    fun join(context: Context, body: JoinBody) = repository.join(context, body)

    fun login(body: LoginBody) = repository.login(body)
}