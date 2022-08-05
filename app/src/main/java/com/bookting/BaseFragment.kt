package com.bookting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bookting.data.SharedHelper
import com.bookting.repository.MainRepository
import com.bookting.viewmodel.MainViewModel
import com.bookting.viewmodel.UserViewModel

open class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {

    lateinit var binding: T
    lateinit var repository: MainRepository
    lateinit var userViewModel: UserViewModel
    lateinit var sharedHelper: SharedHelper
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = MainRepository(requireContext())
        sharedHelper = SharedHelper(requireContext())

        val factory = ViewModelFactory(MainRepository(requireContext()))
        viewModel = ViewModelProvider(requireActivity(), factory).get(MainViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity(), factory).get(UserViewModel::class.java)
        binding.initView()
    }

    inner class ViewModelFactory(val repository: MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass){
            MainViewModel::class.java -> MainViewModel(MainRepository(requireContext()))
            UserViewModel::class.java -> UserViewModel(MainRepository(requireContext()))
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }


    open fun T.initView() = Unit
}