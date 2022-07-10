package com.bookting.view.main

import android.util.Log
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun FragmentHomeBinding.initView() {
        Log.d("체크해보자", sharedHelper.getRefreshToken)
    }
}