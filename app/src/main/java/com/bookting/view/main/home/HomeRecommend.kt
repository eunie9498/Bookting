package com.bookting.view.main.home

import androidx.recyclerview.widget.RecyclerView
import com.bookting.data.HOME
import com.bookting.databinding.HomeRecommendBinding
import com.bookting.ui.SingleBook

class HomeRecommend(val binding: HomeRecommendBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HOME.Recomm) = with(binding) {

        val items = ArrayList<SingleBook>()
        items.clear()
        
        val arr = item.data!!
        arr.forEach {
            val single = SingleBook(root.context)
            single.setBg(it.image_url)
            single.setTxt(it.name)
            items.add(single)
        }

        for (i in items) {
            linear.addView(i)
        }
    }
}