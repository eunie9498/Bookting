package com.bookting.view.main


import android.content.Intent
import android.os.Bundle
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityMainBinding
import com.bookting.view.start.JoinActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

open class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if(!task.isSuccessful){
                    return@OnCompleteListener
                }
                sharedHelper.addPreference(MainConstants.Shared.TOKEN, task.result)
            }
        )
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }
}