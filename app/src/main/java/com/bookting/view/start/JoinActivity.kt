package com.bookting.view.start

import android.widget.Toast
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityJoinBinding
import com.bookting.utils.setStatusTrans

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

        join()
    }

     fun join() = with(binding) {

    }
}