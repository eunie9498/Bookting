package com.bookting.ui

import android.app.Dialog
import android.content.Context
import android.view.View
import com.bookting.databinding.ToastOneBtnBinding

class ToastOneBtn(context: Context) : Dialog(context) {
    var binding: ToastOneBtnBinding = ToastOneBtnBinding.inflate(layoutInflater)

    init {
        setContentView(binding.root)
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setMsg(msg: String) {
        binding.tvMsg.text = msg
    }

    fun setBtn(txt: String, listener: View.OnClickListener) {
        binding.btnToast.text = txt
        binding.btnToast.setOnClickListener(listener)
    }
}