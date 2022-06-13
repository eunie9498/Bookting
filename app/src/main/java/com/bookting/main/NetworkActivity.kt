package com.bookting.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bookting.R
import com.bookting.api.BookAPI
import com.bookting.di.BookComponent
import com.bookting.di.DaggerBookComponent
import javax.inject.Inject

open class NetworkActivity: AppCompatActivity() {
    lateinit var component: BookComponent
    @Inject
    lateinit var api: BookAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = DaggerBookComponent.builder().build()
        component.inject(this)
    }

}