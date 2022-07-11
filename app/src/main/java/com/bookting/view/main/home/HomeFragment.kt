package com.bookting.view.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.HOME
import com.bookting.data.MainConstants
import com.bookting.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun FragmentHomeBinding.initView() = with(binding) {

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HomeAdapter()

        viewModel.getHome()

        viewModel.homeResponse.observe(requireActivity()) {
            if (it.result == MainConstants.SUCCESS)
                (recyclerView.adapter as HomeAdapter).addItems(HOME.Nick("Hi Kong :) "))
        }

    }


}
