package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.R
import com.bookting.databinding.CustomAppbarCenterBinding

class AppBarCenter constructor(
    context: Context,
    attributeSet: AttributeSet
) : ConstraintLayout(context, attributeSet) {

    val binding = CustomAppbarCenterBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarCenter)
        if (a.hasValue(R.styleable.AppBarCenter_setTxt)) {
            binding.tvCenter.text = a.getText(R.styleable.AppBarCenter_setTxt)
        }
        a.recycle()
    }
}