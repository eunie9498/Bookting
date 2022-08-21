package com.bookting.view.start

import android.util.Log
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.JoinBody
import com.bookting.data.LoginBody
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityJoinBinding
import com.google.gson.Gson
import java.util.*

class JoinActivity : BaseActivity<ActivityJoinBinding>(R.layout.activity_join) {

    override fun ActivityJoinBinding.onCreate() {
        act = this@JoinActivity

        appBar.binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun join() = with(binding) {
        val sex = if (radioMan.isSelected) MainConstants.MAN else MainConstants.WOMAN
        val body = JoinBody(
            email = idField.getEt(),
            nickname = nickField.getEt(),
            password = sharedHelper.newEncrypt(pwField.getEt().toByteArray()),
            secret_key = sharedHelper.getFbToken.substring(0, 32), sex
        )
        userViewModel.join(body)
        userViewModel.joinResponse.observe(this@JoinActivity) {
            if (it.result == MainConstants.SUCCESS) {
                userViewModel.login(
                    LoginBody(
                        email = idField.getEt(),
                        sharedHelper.newEncrypt(pwField.getEt().toByteArray()),
                        sharedHelper.getFbToken.substring(0, 32)
                    )
                )

                userViewModel.loginResponse.observe(this@JoinActivity) {
                    if (it.result == MainConstants.SUCCESS) {
                        moveToMain()
                        repository.sharedHelper.addPreference(
                            MainConstants.Shared.REFRESH_TOKEN,
                            it.data!!.refresh_token
                        )
                        repository.sharedHelper.addPreference(
                            MainConstants.Shared.ACCESS_TOKEN,
                            it.data!!.access_token
                        )
                    }
                }
            } else {
                showBtnOneDlg(
                    getString(R.string.join_err_title),
                    getString(R.string.join_err_msg),
                    getString(R.string.ok)
                )
            }
        }
    }
}