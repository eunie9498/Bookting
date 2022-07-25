package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.databinding.SingleBookBinding
import com.bookting.utils.setRoundImg

class SingleBook constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val binding = SingleBookBinding.inflate(LayoutInflater.from(context), this, true)

    fun setBg(img: String) = with(binding) {
        bookImg.setRoundImg(img)
    }

    fun setTxt(txt: String) = with(binding) {
        tvBook.text = txt
    }
}