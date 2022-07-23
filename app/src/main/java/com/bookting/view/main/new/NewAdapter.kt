package com.bookting.view.main.new

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bookting.data.GetBookData
import com.bookting.databinding.NewBookItemBinding

class NewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<GetBookData>()

    fun addItem(arr: List<GetBookData>) {
        items = arr.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as NewHolder
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewHolder(
            NewBookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class NewHolder(val binding: NewBookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetBookData) {
            binding.item = item
            binding.bookItem.setBg(item.image_url ?: "")
        }
    }

    override fun getItemCount() = items.size

}