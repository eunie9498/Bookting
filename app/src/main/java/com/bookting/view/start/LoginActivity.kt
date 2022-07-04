package com.bookting.view.start

import android.util.Log
import androidx.core.view.get
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.LoginBody
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityLoginBinding
import com.bookting.utils.setFullScreen
import com.bookting.utils.setImg
import com.bookting.utils.setStatusTrans
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {


    override fun ActivityLoginBinding.onCreate() {

        binding.act = this@LoginActivity
        binding.appBar.binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        this@LoginActivity.setFullScreen()
      
    }

    fun login()
    {
        viewModel.login(LoginBody(
            email = binding.idField.getEt(),
            sharedHelper.newEncrypt(binding.pwField.getEt().toByteArray()),
            sharedHelper.getFbToken.substring(0, 32)
        ))
    }
}