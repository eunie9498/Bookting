package com.bookting.view.add

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bookting.R
import com.bookting.data.TagItem
import com.bookting.databinding.TagItemListBinding
import com.bookting.utils.dpToPx
import com.google.android.flexbox.FlexboxLayout

class AlreadyAdapter(val listener: AlreadyListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<TagItem>()

    interface AlreadyListener {
        fun clickTag(item: TagItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as AlreadyHolder
        holder.bind(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AlreadyHolder(
            TagItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    inner class AlreadyHolder(val binding: TagItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: List<TagItem>) = with(binding) {
            val tvArr = ArrayList<TextView>()

            for (tagItem in item) {
                val tv = TextView(root.context)
                val params = FlexboxLayout.LayoutParams(
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(
                    4F.dpToPx(root.context).toInt(),
                    4F.dpToPx(root.context).toInt(),
                    4F.dpToPx(root.context).toInt(),
                    4F.dpToPx(root.context).toInt()
                )
                tv.background =
                    ContextCompat.getDrawable(root.context, R.drawable.tag_blue_selector)
                tv.gravity = Gravity.CENTER
                tv.text = "# ${tagItem.name}"
                tv.setPadding(
                    10F.dpToPx(root.context).toInt(), 8F.dpToPx(root.context).toInt(),
                    10F.dpToPx(root.context).toInt(), 8F.dpToPx(root.context).toInt()
                )
                tv.layoutParams = params
                tv.setTextColor(ContextCompat.getColor(root.context, R.color.deep_blue900))
                tv.textSize = 12F
                tv.setOnClickListener {
                    if (item.filter { it.selected == true }.size < 5) {
                        it.isSelected = !it.isSelected
                        tagItem.selected = it.isSelected
                        listener.clickTag(tagItem)
                    } else {
                        if (it.isSelected) {
                            tagItem.selected = false
                            it.isSelected = false
                        }
                    }
                }
                tvArr.add(tv)
            }


            tvArr.forEach {
                tagList.addView(it)
            }
        }
    }

    override fun getItemCount() = 1

    fun addItem(newList: MutableList<TagItem>) {
        val callback = DiffUtilCallback(items, newList)
        val result = DiffUtil.calculateDiff(callback)
        items = newList
        result.dispatchUpdatesTo(this)
    }

    class DiffUtilCallback(private val oldList: List<TagItem>, private val newList: List<TagItem>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}