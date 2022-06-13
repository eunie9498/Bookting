package com.bookting.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityMainBinding
import com.bookting.view.start.JoinActivity

open class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
//    lateinit var component: BookComponent
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        component = DaggerBookComponent.builder().build()
//        component.inject(this)
//
//        val intent = Intent(this, JoinActivity::class.java)
//        startActivity(intent)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }
}