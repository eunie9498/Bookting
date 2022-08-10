package com.bookting.view.bookshelf

import androidx.recyclerview.widget.RecyclerView
import com.bookting.R
import com.bookting.data.SHELF
import com.bookting.databinding.ShelfTopBinding

class ShelfTop(val nick: String, val month: String, val binding: ShelfTopBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SHELF.ShelfTop) = with(binding) {
        tvTitle.text = root.context.getString(R.string.shelf_title_by_user, nick)
        tvCount.text = root.context.getString(R.string.shelf_count, item.count)

    }
}