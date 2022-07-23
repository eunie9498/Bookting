package com.bookting.view.main.home

import androidx.recyclerview.widget.RecyclerView
import com.bookting.data.HOME
import com.bookting.databinding.HomeBadgeHolderBinding

class HomeBadge(val binding: HomeBadgeHolderBinding, val listener: HomeListener) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HOME.Badge) = with(binding) {
        holder = this@HomeBadge
        homeListener = listener
    }
}