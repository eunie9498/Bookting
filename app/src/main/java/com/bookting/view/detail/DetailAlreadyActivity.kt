package com.bookting.view.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.data.GetAlreadyBookItem
import com.bookting.data.MainConstants
import com.bookting.databinding.ActivityDetailAlreadyBinding
import com.bookting.databinding.AlreadyListHolderBinding
import com.bookting.ui.GridBook
import com.bookting.utils.getCurrentTimeBefore
import com.google.gson.Gson

class DetailAlreadyActivity :
    BaseActivity<ActivityDetailAlreadyBinding>(R.layout.activity_detail_already) {

    var page = 0

    override fun ActivityDetailAlreadyBinding.onCreate() {
        val arr = getCurrentTimeBefore(6)
        val spinnerAdapter = ArrayAdapter(this@DetailAlreadyActivity, R.layout.spinner_txt, arr)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //todo : viewpager 변경
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }
        }

        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(viewPagerScroll)
    }

    private fun drawGrid(arr: List<GetAlreadyBookItem>) = with(binding) {
        val adapter = ViewPagerAdapter()
        adapter.addItems(arr)
        viewPager.adapter = adapter
        (viewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        indicator.attachTo(viewPager)
    }

    inner class ViewPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var items = ArrayList<GetAlreadyBookItem>()

        fun addItems(arr: List<GetAlreadyBookItem>) {
            items.addAll(arr)
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder as PageHolder
            holder.bind(items)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val binding =
                AlreadyListHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PageHolder(binding)
        }

        inner class PageHolder(val binding: AlreadyListHolderBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(item: List<GetAlreadyBookItem>) = with(binding) {
                val gridArr = ArrayList<GridBook>()
                item.forEach {
                    val grid = GridBook(root.context)
                    grid.apply {
                        setTitle(it.book_name)
                    }
                    gridArr.add(grid)
                }
                gridArr.forEach {
                    flexLayout.addView(it)
                }
            }
        }

        override fun getItemCount() = 1
    }


    fun initView() {
        userViewModel.GetAlreadyRead(page, MainConstants.LIMIT_GRID_SIZE)
        userViewModel.alreadyDataResponse.observe(this@DetailAlreadyActivity) {
            if (it.result == MainConstants.SUCCESS) {
                Log.d("체크체크체크체크", Gson().toJson(userViewModel.alreadyDataResponse.value!!.data))
                drawGrid(userViewModel.alreadyDataResponse.value!!.data!!)
            }
        }
        binding.viewPager.registerOnPageChangeCallback(viewPagerScroll)
    }

    fun addScrollItem(page: Int) {
        userViewModel.GetAlreadyRead(page, MainConstants.LIMIT_GRID_SIZE)
    }

    val viewPagerScroll = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            addScrollItem(position)
        }
    }
}