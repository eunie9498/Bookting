package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.R
import com.bookting.databinding.AppBarEndTxtBinding

class AppBarEndTxt constructor(
    context: Context,
    attributeSet: AttributeSet
) : ConstraintLayout(context, attributeSet) {

    val appBarBinding = AppBarEndTxtBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarEndTxt)
        if (a.hasValue(R.styleable.AppBarEndTxt_setTxt))
            appBarBinding.tvEnd.text = a.getText(R.styleable.AppBarEndTxt_setTxt)
        a.recycle()
    }

    fun setTxtListener(listener: OnClickListener) {
        appBarBinding.tvEnd.setOnClickListener(listener)
    }
}