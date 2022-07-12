package com.bookting.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bookting.data.HOME
import com.bookting.data.MainConstants
import com.bookting.databinding.HomeBadgeHolderBinding
import com.bookting.databinding.HomeTopHolderBinding

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val itemList = mutableListOf<HOME>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeTop -> {
                holder.bind(itemList[position] as HOME.Nick)
            }
            is HomeBadge -> {
                holder.bind(itemList[position] as HOME.Badge)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MainConstants.ViewHolder.TOP -> {
                return HomeTop(
                    HomeTopHolderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            MainConstants.ViewHolder.HEADER -> {
                return HomeBadge(
                    HomeBadgeHolderBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            else -> {
                return HomeTop(
                    HomeTopHolderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    fun addItems(item: HOME.Nick) {
        this.itemList.add(item)
        notifyDataSetChanged()
    }

    fun addBadge(item: HOME.Badge){
        this.itemList.add(item)
        notifyDataSetChanged()
    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        when (itemList[position]) {
            is HOME.Nick -> {
                return MainConstants.ViewHolder.TOP
            }

            is HOME.Badge -> {
                return MainConstants.ViewHolder.HEADER
            }

            else -> {
                return -1
            }
        }
    }

}