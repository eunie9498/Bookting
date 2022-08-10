package com.bookting.view.bookshelf

import com.bookting.BaseActivity
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.data.SHELF
import com.bookting.databinding.ActivityShelfBinding
import com.bookting.utils.getCurrentTime

class ShelfActivity : BaseFragment<ActivityShelfBinding>(R.layout.activity_shelf) {

    override fun ActivityShelfBinding.initView() {
        recyclerView.adapter = ShelfAdapter(sharedHelper.getUserNick, getCurrentTime().substring(0,6))

        viewModel.getShelfByUser(getCurrentTime().substring(0,6))
        viewModel.shelfResponse.observe(this@ShelfActivity) {
            if(it.result == MainConstants.SUCCESS) {

                (recyclerView.adapter as ShelfAdapter).addTop(SHELF.ShelfTop(it.total_count))
                (recyclerView.adapter as ShelfAdapter).addItem(it.data!!)
            }
            else{

            }

        }
    }

}