package com.bookting.view.main.home

import androidx.recyclerview.widget.RecyclerView
import com.bookting.data.HOME
import com.bookting.databinding.HomeRecommendBinding
import com.bookting.ui.SingleBook

class HomeRecommend(val listener: HomeListener, val binding: HomeRecommendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HOME.Recomm) = with(binding) {

        val items = ArrayList<SingleBook>()
        items.clear()

        val arr = item.data!!
        arr.slice(0..2).forEach { best ->
            val single = SingleBook(root.context)
            single.setBg(best.image_url ?: "")
            single.setTxt(best.name ?: "")
            single.setOnClickListener {
                listener.onRecommend(best.id ?: -1)
            }
            items.add(single)
        }

        for (i in items) {
            linear.addView(i)
        }
    }
}