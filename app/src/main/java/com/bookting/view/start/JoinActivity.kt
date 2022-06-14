package com.bookting.view.start

import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityJoinBinding

class JoinActivity : BaseActivity<ActivityJoinBinding>(R.layout.activity_join) {

    override fun ActivityJoinBinding.onCreate() {
        idField.setBg(R.drawable.line_radius10_purple400)
        idField.setHt(root.context.getString(R.string.id))

        pwField.setBg(R.drawable.line_radius10_purple400)
        pwField.setErr(root.context.getString(R.string.pw))

        appBar.binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}