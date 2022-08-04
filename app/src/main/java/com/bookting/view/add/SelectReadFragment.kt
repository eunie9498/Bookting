package com.bookting.view.add

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bookting.R
import com.bookting.data.MainConstants
import com.bookting.databinding.SelectReadFragmentBinding
import com.bookting.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.logging.Handler

class SelectReadFragment(val book_id: Int, val viewModel: MainViewModel) : BottomSheetDialogFragment() {
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

        binding.fragment = this@SelectReadFragment
        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
    }


    fun moveTo(v: ConstraintLayout) {
        when (v) {
            binding.wish -> {
                binding.wish.isSelected = !binding.wish.isSelected
                binding.already.isSelected = !binding.wish.isSelected
                if (binding.wish.isSelected) {
                    val bottomAdd = WishReadFragment(book_id, viewModel)
                    bottomAdd.show(requireActivity().supportFragmentManager, bottomAdd.tag)
                }
            }
            else -> {
                binding.already.isSelected = !binding.already.isSelected
                binding.wish.isSelected = !binding.already.isSelected
                if (binding.already.isSelected) {
                    val i = Intent(requireActivity(), AlreadyReadActivity::class.java)
                    val b = Bundle()
                    b.putInt(MainConstants.BUNDLE_KEY.BOOK_ID, book_id)
                    i.putExtras(b)
                    startActivity(i)
                }
            }
        }
    }

    fun moveBack() {

    }
}