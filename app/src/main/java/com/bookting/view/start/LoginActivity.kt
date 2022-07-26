package com.bookting.view.start

import androidx.lifecycle.Observer
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.LoginBody
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityLoginBinding
import com.bookting.utils.setFullScreen

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun ActivityLoginBinding.onCreate() {

        binding.act = this@LoginActivity
        binding.appBar.binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        this@LoginActivity.setFullScreen()

    }

    fun login() {
        viewModel.login(
            LoginBody(
                email = binding.idField.getEt(),
                sharedHelper.newEncrypt(binding.pwField.getEt().toByteArray()),
                sharedHelper.getFbToken.substring(0, 32)
            )
        )

        viewModel.loginResponse.observe(this, Observer {
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
            } else {
                if ((it.reason ?: "").contains("token")) {
                    showBtnOneDlg(
                        getString(R.string.login_err_title),
                        getString(R.string.login_err_msg_expire),
                        getString(R.string.ok)
                    )
                } else {
                    showBtnOneDlg(
                        getString(R.string.login_err_title),
                        getString(R.string.login_err_msg),
                        getString(R.string.ok)
                    )
                }
            }
        })

    }
}