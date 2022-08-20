package com.bookting

import android.content.Intent
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivitySplashBinding
import com.bookting.view.main.MainActivity
import com.bookting.view.main.StartActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity: BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun ActivitySplashBinding.onCreate() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                sharedHelper.addPreference(MainConstants.Shared.FB_TOKEN, task.result)
            }
        )

        if (sharedHelper.getAccessToken != "") {
            userViewModel.getUserData()
            userViewModel.userDataResponse.observe(this@SplashActivity) {
                if (it.result == MainConstants.SUCCESS) {
                    it.data?.let { data ->
                        sharedHelper.addPreference(MainConstants.Shared.USER_EMAIL, data.email)
                        sharedHelper.addPreference(MainConstants.Shared.USER_NICK, data.nickname)
                        sharedHelper.addPreference(MainConstants.Shared.USER_PROFILE, data.image_url)
                    }
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    if (it.reason!!.contains("access_token")) {
                        sharedHelper.addPreference(MainConstants.Shared.ACCESS_TOKEN, "")
                        sharedHelper.addPreference(MainConstants.Shared.REFRESH_TOKEN, "")

                        showBtnOneDlg(
                            getString(R.string.login_err_title),
                            getString(R.string.login_err_msg_expire), getString(R.string.ok)
                        ) {
                            val i = Intent(this@SplashActivity, StartActivity::class.java)
                            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                            startActivity(i)
                        }
                    }
                }
            }
        }else{
            val i = Intent(this@SplashActivity, StartActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(i)
        }
    }

}