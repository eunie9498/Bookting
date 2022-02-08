package com.bookting.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bookting.R
import com.bookting.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    override fun initView() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}