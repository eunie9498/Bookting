package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.databinding.EditMemoBinding

class EditMemo constructor(
    context: Context,
    attributeSet: AttributeSet
) : ConstraintLayout(context, attributeSet) {

    val etMemoBinding = EditMemoBinding.inflate(LayoutInflater.from(context), this, true)

}