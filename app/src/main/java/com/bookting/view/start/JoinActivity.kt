package com.bookting.view.start

import android.util.Log
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.JoinBody
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityJoinBinding
import com.google.gson.Gson
import java.util.*

class JoinActivity : BaseActivity<ActivityJoinBinding>(R.layout.activity_join) {

    override fun ActivityJoinBinding.onCreate() {
        act = this@JoinActivity

        idField.apply {
            setHtColor(R.color.purple400)
            setHt(root.context.getString(R.string.id))
        }
        pwField.apply {
            setHt(root.context.getString(R.string.pw))
            setHtColor(R.color.purple400)
        }

        appBar.binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        nickField.apply {
            setHtColor(R.color.purple400)
            setHt(root.context.getString(R.string.nickname))
        }
    }

    fun join() = with(binding) {
        val sex = if (radioMan.isSelected) MainConstants.MAN else MainConstants.WOMAN
        val body = JoinBody(
            email = idField.getEt(),
            nickname = nickField.getEt(),
            password = sharedHelper.newEncrypt(pwField.getEt().toByteArray()),
            secret_key = sharedHelper.getFbToken.substring(0, 32),
            sex
        )
        viewModel.join(this@JoinActivity, body)
    }
}