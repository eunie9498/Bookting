package com.bookting.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bookting.api.BookAPI
import com.bookting.data.BooktingData
import com.bookting.di.BookComponent
import com.bookting.di.DaggerBookComponent
import javax.inject.Inject

open class NetworkActivity: AppCompatActivity() {
    lateinit var component: BookComponent
    @Inject
    lateinit var api: BookAPI
    @Inject
    lateinit var data: BooktingData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = DaggerBookComponent.builder().build()
        component.inject(this)

    }

}