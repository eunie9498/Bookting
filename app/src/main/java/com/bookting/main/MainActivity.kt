package com.bookting.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bookting.R
import com.bookting.api.BookAPI
import com.bookting.di.BookComponent
import com.bookting.di.DaggerBookComponent
import javax.inject.Inject

open class MainActivity : AppCompatActivity(){


    lateinit var component: BookComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = DaggerBookComponent.builder().build()
        component.inject(this)

        val fragment = MainFragment()

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

}