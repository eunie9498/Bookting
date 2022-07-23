package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.R
import com.bookting.databinding.BookListItemBinding
import com.bookting.utils.setImg
import com.bookting.utils.setRoundImg

class BookListItem constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val binding = BookListItemBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.BookListItem)
        if (a.hasValue(R.styleable.BookListItem_setName))
            binding.tvTitle.text = a.getText(R.styleable.BookListItem_setName) ?: ""
        if (a.hasValue(R.styleable.BookListItem_setAuthor))
            binding.tvAuthor.text = a.getText(R.styleable.BookListItem_setAuthor) ?: ""
        if (a.hasValue(R.styleable.BookListItem_setPublisher))
            binding.tvPublisher.text = a.getText(R.styleable.BookListItem_setPublisher) ?: ""

        a.recycle()
    }

    fun setBg(img: String) = with(binding) {
        bookImg.setRoundImg(img)
    }

    fun setName(txt: String) = with(binding) {
        tvTitle.text = txt
    }

    fun setPublisher(publisher: String) = with(binding) {
        tvPublisher.text = publisher
    }

    fun setAuthor(author: String) = with(binding) {
        tvAuthor.text = author
    }
}