package com.bookting.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.bookting.databinding.DlgOneBtnBinding

class DlgOneBtn(context: Context) : Dialog(context) {

    var binding: DlgOneBtnBinding = DlgOneBtnBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun setTitle(title: String) {
        binding.dlgTitle.text = title
    }

    fun setListener(txt: String, listener: View.OnClickListener? = null) {
        binding.dlgBtn.text = txt
        if (listener == null)
            binding.dlgBtn.setOnClickListener {
                dismiss()
            }
        else
            binding.dlgBtn.setOnClickListener(listener)
    }

    fun setMsg(msg: String) {
        binding.tvMsg.text = msg
    }
}