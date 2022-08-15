package com.bookting.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.bookting.R
import com.bookting.databinding.GridItemBinding
import com.bookting.utils.dpToPx
import com.bookting.utils.setImg
import com.google.android.flexbox.FlexboxLayout

class GridBook constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val gridBinding = GridItemBinding.inflate(LayoutInflater.from(context),this, true)

    fun setBooksImg(img: String){
        gridBinding.img.setImg(img)
    }

    fun setTitle(title: String){
        gridBinding.tvTitle.text = title
    }

    fun setGrade(star: Int){
        var arr = ArrayList<ImageView>()
        repeat(star){
            val img = ImageView(context)
            img.setBackgroundResource(R.drawable.fav_blue)
            val params = LinearLayout.LayoutParams(
                dpToPx(context.resources, 15f).toInt(),
                dpToPx(context.resources, 15f).toInt()
            )
            img.layoutParams = params
            arr.add(img)
        }
        arr.forEach {
            gridBinding.linear.addView(it)
        }
    }
}