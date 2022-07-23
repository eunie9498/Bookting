package com.bookting.view.main.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.bookting.BaseActivity
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.HOME
import com.bookting.data.MainConstants
import com.bookting.data.HOME.Recomm
import com.bookting.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), HomeListener {

    override fun FragmentHomeBinding.initView() = with(binding) {

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HomeAdapter(this@HomeFragment)

        viewModel.getHome()
        viewModel.bestSeller()

        viewModel.homeResponse.observe(requireActivity()) {
            if (it.result == MainConstants.SUCCESS) {
                (recyclerView.adapter as HomeAdapter).addItems(HOME.Nick("Hi Kong :) "))
                (recyclerView.adapter as HomeAdapter).addBadge(HOME.Badge)
            }
        }

        viewModel.bookData.observe(requireActivity()) {
            (recyclerView.adapter as HomeAdapter).addRecomm(HOME.Recomm(it))
        }
    }

    override fun onBest() {
        (requireActivity() as BaseActivity<*>).moveToBestAct()
    }
}
