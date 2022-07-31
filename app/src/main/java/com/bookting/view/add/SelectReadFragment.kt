package com.bookting.view.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bookting.R
import com.bookting.databinding.SelectReadFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectReadFragment : BottomSheetDialogFragment() {
    lateinit var binding: SelectReadFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SelectReadFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int = R.style.BottomSheetDialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wish.isSelected = false
        binding.fragment = this@SelectReadFragment
        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
        binding.already.isSelected = true
    }


    fun moveTo(v: ConstraintLayout) {
        when (v) {
            binding.wish -> {
                binding.wish.isSelected = !binding.wish.isSelected
                binding.already.isSelected = !binding.wish.isSelected
            }
            else -> {
                binding.already.isSelected = !binding.already.isSelected
                binding.wish.isSelected = !binding.already.isSelected
            }
        }
    }

    fun moveBack() {
       
    }
}