package com.bookting.view.start

import androidx.core.view.get
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.LoginBody
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityLoginBinding
import com.bookting.utils.setStatusTrans
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun ActivityLoginBinding.onCreate() {

        binding.act = this@LoginActivity
        binding.appBar.binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.email = binding.idField.getEt()
        binding.pw = binding.pwField.getEt()

    }

    fun login(id: String, pw: String) {
        api.run {
            login(
                LoginBody(
                    email = id,
                    sharedHelper.newEncrypt(pw.toByteArray()), sharedHelper.getFbToken.substring(0,32)
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.result == MainConstants.SUCCESS) {

                        sharedHelper.addPreference(
                            MainConstants.Shared.ACCESS_TOKEN,
                            it.data!!.access_token
                        )
                        sharedHelper.addPreference(
                            MainConstants.Shared.REFRESH_TOKEN,
                            it.data!!.refresh_token
                        )

                        moveToMain()

                    } else {
                        showToastOneBtn(
                            getString(R.string.fail),
                            getString(R.string.login_fail),
                            getString(R.string.ok),
                            {

                            })
                    }
                }
        }
    }
}