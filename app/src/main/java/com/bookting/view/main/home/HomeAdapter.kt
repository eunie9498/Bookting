package com.bookting.view.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bookting.data.GetBookData
import com.bookting.data.HOME
import com.bookting.data.MainConstants
import com.bookting.databinding.HomeAnalyHolderBinding
import com.bookting.databinding.HomeBadgeHolderBinding
import com.bookting.databinding.HomeRecommendBinding
import com.bookting.databinding.HomeTopHolderBinding
import com.google.gson.Gson
import java.lang.Exception

class HomeAdapter(val nick: String, val listener: HomeListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList = mutableListOf<HOME>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeTop -> {
                holder.bind(itemList[position] as HOME.Nick)
            }
            is HomeBadge -> {
                holder.bind(itemList[position] as HOME.Badge)
            }
            is HomeRecommend -> {
                holder.bind(itemList[position] as HOME.Recomm)
            }
            is HomeAnalysis -> {
                holder.bind(itemList[position] as HOME.Analysis)
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
                    ), listener
                )
            }

            MainConstants.ViewHolder.RECOMM -> {
                return HomeRecommend(
                    listener,
                    HomeRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            MainConstants.ViewHolder.ANALYSIS -> {
                return HomeAnalysis(
                    nick,
                    HomeAnalyHolderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
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
        this.itemList.clear()
        this.itemList.add(item)
    }

    fun addBadge(item: HOME.Badge) {
        this.itemList.add(item)

    }

    fun addRecomm(item: HOME.Recomm) {
        this.itemList.add(item)
        notifyDataSetChanged()
    }

    fun addAnalys(item: HOME.Analysis) {
        this.itemList.add(item)
        notifyDataSetChanged()
    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int = when (itemList[position]) {
        is HOME.Nick -> {
            MainConstants.ViewHolder.TOP
        }

        is HOME.Badge -> {
            MainConstants.ViewHolder.HEADER
        }

        is HOME.Recomm -> {
            MainConstants.ViewHolder.RECOMM
        }

        is HOME.Analysis -> {
            MainConstants.ViewHolder.ANALYSIS
        }

        else -> {
            throw Exception("ViewType Err")
        }
    }
}