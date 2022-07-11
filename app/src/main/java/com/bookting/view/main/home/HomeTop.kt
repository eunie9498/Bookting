package com.bookting.view.main.home


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bookting.R
import com.bookting.data.HOME
import com.bookting.databinding.HomeTopHolderBinding
import kotlin.random.Random

class HomeTop(val binding: HomeTopHolderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HOME.Nick) = with(binding) {
        binding.topStr = item.nickName
    }
}