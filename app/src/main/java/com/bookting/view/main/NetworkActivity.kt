package com.bookting.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bookting.api.BookAPI
import com.bookting.data.SharedHelper
import com.bookting.di.BookComponent
import com.bookting.di.BookNetworkModule
import com.bookting.di.DaggerBookComponent
import javax.inject.Inject

open class NetworkActivity: AppCompatActivity() {
    lateinit var component: BookComponent
    @Inject
    lateinit var api: BookAPI

    @Inject
    lateinit var sharedHelper: SharedHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component =  DaggerBookComponent.builder()
            .bookNetworkModule(BookNetworkModule(this))
            .build()
        component.inject(this)
    }

}