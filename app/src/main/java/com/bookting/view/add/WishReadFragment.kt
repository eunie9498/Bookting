package com.bookting.view.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.databinding.FragmentHomeBinding
import com.bookting.databinding.WishReadFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WishReadFragment : BottomSheetDialogFragment() {
    lateinit var fragBinding: WishReadFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragBinding = WishReadFragmentBinding.inflate(inflater, container, false)
        return fragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}