package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.R
import com.bookting.databinding.MainBadgeBinding

class MainBadge(
    context: Context,
    attributes: AttributeSet? = null
) : ConstraintLayout(context, attributes) {

    var clickListener: View.OnClickListener? = null
    val binding = MainBadgeBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(attributes, R.styleable.MainBadge)
        if (a.hasValue(R.styleable.MainBadge_setTxt))
            binding.tvBadge.text = a.getText(R.styleable.MainBadge_setTxt)
        a.recycle()
    }

    fun setEmoji(emojiTxt: String) {
        binding.tvEmoji.text = emojiTxt
    }

    fun setListener(listener: OnClickListener) {
        clickListener = listener
        binding.wholeBadge.setOnClickListener(clickListener)
    }
}