package com.bookting.view

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bookting.R
import com.bookting.api.BookAPI
import com.bookting.databinding.ActivityMainBinding
import com.bookting.di.BookComponent
import com.bookting.di.DaggerBookComponent
import javax.inject.Inject

open class MainActivity : AppCompatActivity(){

    @Inject
    lateinit var api: BookAPI

    lateinit var component: BookComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerBookComponent.builder().build()
        component.inject(this)
    }

}