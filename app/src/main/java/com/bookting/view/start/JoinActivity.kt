package com.bookting.view.start

import android.widget.Toast
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.JoinBody
import com.bookting.databinding.ActivityJoinBinding
import com.bookting.utils.setStatusTrans
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

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
         api.run {
             joinUser(JoinBody( email = idField.getEt(), name="test",
                 password = sharedHelper.newEncrypt(pwField.getEt().toByteArray()),sharedHelper.getToken, "W"))
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe {
                     Toast.makeText(this@JoinActivity, "가입완료", Toast.LENGTH_SHORT).show()

                 }
         }
    }
}