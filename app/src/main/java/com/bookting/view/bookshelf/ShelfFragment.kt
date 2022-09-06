package com.bookting.view.bookshelf

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.data.SHELF
import com.bookting.databinding.ShelfFragmentBinding
import com.bookting.databinding.ShelfPagerItemBinding
import com.bookting.ui.GridBook
import com.bookting.utils.getCurrentTime
import com.bookting.utils.getCurrentTimeBefore
import com.bookting.utils.showToast
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class ShelfFragment : BaseFragment<ShelfFragmentBinding>(R.layout.shelf_fragment) {

    var page = 0
    var shelfDatas = ArrayList<SHELF.UserShelfData>()

    override fun ShelfFragmentBinding.initView() {
        shelfDatas.clear()
        init()
        viewModel.getShelfByUser(
            getCurrentTime().substring(0, 6),
            page,
            MainConstants.LIMIT_GRID_SIZE
        )
        Log.d("체크체크체크체크", sharedHelper.getAccessToken)
    }

    private fun getShelf(date: String) {
        viewModel.shelfResponse.observe(this@ShelfFragment) {
            showToast(requireContext(), page.toString())
            if (it.result == MainConstants.SUCCESS) {
                binding.tvEmpty.isVisible = it.data?.size == 0 || it.data == null

                if (it.data?.size == 0 || it.data == null) {
                    drawGrid(emptyList())
                    binding.tvCount.text = getString(R.string.shelf_count, 0)
                } else {
                    it.data?.let { data ->
                        binding.tvTitle.text =
                            getString(R.string.shelf_title_by_user, sharedHelper.getUserNick)
                        binding.tvCount.text = getString(R.string.shelf_count, it.total_count)
                        data.forEach { shelf ->
                            shelfDatas.add(shelf)
                        }

                        if (it.has_next)
                            moreShelf(date)
                        else
                            drawGrid(shelfDatas)
                    }
                }
            }
        }
    }

    private fun moreShelf(date: String) {
        viewModel.getShelfByUser(date, ++page, MainConstants.LIMIT_GRID_SIZE)
    }

    private fun drawGrid(arr: List<SHELF.UserShelfData>) = with(binding) {
        val adapter = ViewPagerAdapter()
        adapter.addItems(arr)

        viewPager.adapter = adapter
        (viewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        dotsIndicator.attachTo(viewPager)
    }

    fun drawMoreGrid(arr: List<SHELF.UserShelfData>) = with(binding) {
        arr.forEach {
            shelfDatas.add(it)
        }
        (viewPager.adapter as ViewPagerAdapter).addMoreItems(shelfDatas)
    }

    private fun init() = with(binding) {
        val arr = getCurrentTimeBefore(6)
        val spinnerAdapter =
            ArrayAdapter(root.context, R.layout.spinner_txt, arr)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                page = 0
                val date = arr[pos].replace(MainConstants.YEAR, "").replace(MainConstants.MONTH, "")
                    .replace(" ", "")
                getShelf(date)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
    }

    inner class ViewPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var items = ArrayList<SHELF.UserShelfData>()

        fun addItems(arr: List<SHELF.UserShelfData>) {
            items.addAll(arr)
            notifyDataSetChanged()
        }

        fun addMoreItems(arr: List<SHELF.UserShelfData>) {
            items.addAll(arr)
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder as PagerHolder
            holder.bind(items)
        }

        override fun getItemCount() = page

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val binding =
                ShelfPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PagerHolder(binding)
        }

        inner class PagerHolder(val binding: ShelfPagerItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(item: List<SHELF.UserShelfData>) = with(binding) {

                val gridArr = ArrayList<GridBook>()

                item.forEach {
                    val grid = GridBook(root.context)
                    grid.apply {
                        setTitle(it.book_name)
                        setBooksImg(it.image_url)
                        setGrade(it.rate)
                    }
                    gridArr.add(grid)
                }

                gridArr.forEach {
                    flexLayout.addView(it)
                }
            }
        }
    }
}