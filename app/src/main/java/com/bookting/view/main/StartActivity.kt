package com.bookting.view.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityStartBinding
import com.bookting.view.start.JoinActivity
import com.bookting.view.start.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

open class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                sharedHelper.addPreference(MainConstants.Shared.FB_TOKEN, task.result)
            }
        )
        binding.activity = this

        if (sharedHelper.getAccessToken != "") {
            userViewModel.getUserData()
            userViewModel.userDataResponse.observe(this@StartActivity) {
                it.data.let { data ->
                    sharedHelper.addPreference(MainConstants.Shared.USER_EMAIL, data.email)
                    sharedHelper.addPreference(MainConstants.Shared.USER_NICK, data.nickname)
                }
            }
            goMain()
        }
    }

    fun goJoin() {
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }

    fun goLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun goMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}