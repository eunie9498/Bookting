package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bookting.R
import com.bookting.databinding.CustomInputTxtBinding

class InputTxt constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val binding = CustomInputTxtBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(R.styleable.InputTxt)
        a.recycle()
    }

    fun getEt(): String {
        return binding.tvInput.text.toString()
    }

    fun setHt(hint: String) {
        binding.inputLayout.hint = hint
    }

    fun setBg(bg: Int) {
        binding.tvInput.background = ContextCompat.getDrawable(binding.root.context, bg)
    }

}