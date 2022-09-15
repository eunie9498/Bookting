package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.R
import com.bookting.databinding.SearchBarBinding

class SearchBar constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val searchbarBinding = SearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    init {

    }
}