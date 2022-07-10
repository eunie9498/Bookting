package com.bookting.view.main

import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityMainBinding
import com.bookting.ui.TabItem

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    lateinit var tabs: Array<TabItem>

    override fun ActivityMainBinding.onCreate() {
        binding.activity = this@MainActivity
        tabs = arrayOf(binding.tabHome, binding.tabList, binding.tabSearch, binding.tabSetting)
        tabClick(binding.tabHome)
    }

    fun tabClick(v: TabItem) = with(binding) {
        tabs.forEach { item ->
            item.isSelected = item == v
        }

        when (v) {
            tabHome -> {
                val fragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
            }
            tabList -> {

            }
            tabSearch -> {

            }
            tabSetting -> {

            }
            else -> {

            }
        }
    }
}