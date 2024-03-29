package com.bookting.view.main.best

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bookting.data.GetBookData
import com.bookting.databinding.BestSellerItemBinding

class BestAdapter(val listener: BestListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<GetBookData>()

    fun addItem(item: List<GetBookData>) {
        items = item.toMutableList()
    }

    interface BestListener {
        fun onDetail(bookId: Int)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as BestItem
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BestItem(
            BestSellerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class BestItem(val binding: BestSellerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetBookData) {
            binding.item = item
            binding.bookItem.setBg(item.image_url ?: "")
            binding.bookItem.setOnClickListener {
                listener.onDetail(item.id ?: -1)
            }
        }
    }

    override fun getItemCount() = items.size

}