package com.bookting.view.start

import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.JoinBody
import com.bookting.databinding.ActivityJoinBinding
import com.bookting.repository.MainRepository

class JoinActivity : BaseActivity<ActivityJoinBinding>(R.layout.activity_join) {
    lateinit var repository: MainRepository

    override fun ActivityJoinBinding.onCreate() {
        act = this@JoinActivity
        repository = MainRepository()

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
        repository.join(
            JoinBody(
                email = idField.getEt(), name = nickField.getEt(), password = pwField.getEt(),
                secret_key = sharedHelper.newEncrypt(pwField.getEt().toByteArray()), "W"
            )
        )
    }
}