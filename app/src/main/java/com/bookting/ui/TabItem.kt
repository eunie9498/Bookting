package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bookting.R
import com.bookting.databinding.TabItemBinding

class TabItem constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {
    val binding = TabItemBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.TabItem)
        if (a.hasValue(R.styleable.TabItem_setTxt))
            binding.tvTab.text = a.getText(R.styleable.TabItem_setTxt)
        if (a.hasValue(R.styleable.TabItem_tabBg))
            binding.imgIcon.background =
                ContextCompat.getDrawable(context, a.getResourceId(R.styleable.TabItem_tabBg, 0))
        a.recycle()
    }


}
