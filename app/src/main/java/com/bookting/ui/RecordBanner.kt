package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.databinding.RecordBannerBinding

class RecordBanner constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val binding = RecordBannerBinding.inflate(LayoutInflater.from(context), this, true)

}