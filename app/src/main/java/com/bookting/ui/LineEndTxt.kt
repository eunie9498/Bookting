package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.R
import com.bookting.databinding.LineEndTxtBinding

class LineEndTxt constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val lineEndTxtBinding = LineEndTxtBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(R.styleable.LineEndTxt)

        if (a.hasValue(R.styleable.LineEndTxt_setTxt))
            lineEndTxtBinding.tvTitle.text = a.getText(R.styleable.LineEndTxt_setTxt)
        if (a.hasValue(R.styleable.LineEndTxt_setEndTxt))
            lineEndTxtBinding.tvEnd.text = a.getText(R.styleable.LineEndTxt_setEndTxt)

        a.recycle()
    }
}