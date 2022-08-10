package com.bookting.view.bookshelf

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bookting.data.HOME
import com.bookting.data.MainConstants
import com.bookting.data.SHELF
import com.bookting.databinding.HomeTopHolderBinding
import com.bookting.databinding.ShelfTopBinding
import com.bookting.view.main.home.HomeTop
import java.lang.Exception

class ShelfAdapter(val nick: String, val month: String) :
    ListAdapter<SHELF, RecyclerView.ViewHolder>(diff) {

    var itemList = ArrayList<SHELF>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShelfTop -> {
                holder.bind(itemList[position] as SHELF.ShelfTop)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MainConstants.ViewHolder.TOP -> {
                return ShelfTop(
                    nick,
                    month,
                    ShelfTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> {
                return ShelfTop(
                    nick,
                    month,
                    ShelfTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    fun addItem(item: List<SHELF.UserShelfData>){
        itemList.addAll(item)
    }

    fun addTop(top: SHELF.ShelfTop) {
        itemList.clear()
        itemList.add(top)
    }


    object diff : DiffUtil.ItemCallback<SHELF>() {
        override fun areItemsTheSame(oldItem: SHELF, newItem: SHELF): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SHELF, newItem: SHELF): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int = when (itemList[position]) {
        is SHELF.ShelfTop -> {
            MainConstants.ViewHolder.TOP
        }

        is SHELF.UserShelfData -> {
            MainConstants.ViewHolder.HEADER
        }
    }
}