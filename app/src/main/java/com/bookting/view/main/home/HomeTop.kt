package com.bookting.view.main.home


import androidx.recyclerview.widget.RecyclerView
import com.bookting.R
import com.bookting.data.HOME
import com.bookting.databinding.HomeTopHolderBinding

class HomeTop(val binding: HomeTopHolderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HOME.Nick) = with(binding) {
        topStr = root.context.getString(R.string.home_recomm_1, item.nickName)
    }
}