package com.bookting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bookting.data.SharedHelper
import com.bookting.databinding.FragmentMainBinding
import com.bookting.di.BookComponent
import com.bookting.di.BookNetworkModule
import com.bookting.di.DaggerBookComponent
import javax.inject.Inject

open class MainFragment : Fragment() {

    @Inject
    lateinit var sharedHelper: SharedHelper

    lateinit var mainBinding: FragmentMainBinding

    var component: BookComponent = DaggerBookComponent.builder()
        .bookNetworkModule(BookNetworkModule(requireContext()))
        .build()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mainBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
    }

}