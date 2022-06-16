package com.bookting.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bookting.BaseActivity
import com.bookting.FbMessaging
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityMainBinding
import com.bookting.utils.setStatusTrans
import com.bookting.view.start.JoinActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

open class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if(!task.isSuccessful){
                    Log.e("FCM Err", task.exception.toString())
                    return@OnCompleteListener
                }
                sharedHelper.addPreference(MainConstants.Shared.TOKEN, task.result)
            }
        )
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }
}