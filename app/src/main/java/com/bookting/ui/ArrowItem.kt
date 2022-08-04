package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.R
import com.bookting.databinding.ArrowItemBinding

class ArrowItem constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val arrowBinding = ArrowItemBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.ArrowItem)
        if (a.hasValue(R.styleable.ArrowItem_setTxt))
            arrowBinding.tvTitle.text = a.getText(R.styleable.ArrowItem_setTxt)
        a.recycle()
    }

    fun setListener(listener: View.OnClickListener) {
        arrowBinding.btnNext.setOnClickListener {
            listener
        }
    }
}