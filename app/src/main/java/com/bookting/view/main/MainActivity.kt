package com.bookting.view.main

import android.content.Intent
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.databinding.ActivityMainBinding
import com.bookting.ui.TabItem
import com.bookting.view.bookshelf.ShelfActivity
import com.bookting.view.main.home.HomeFragment
import com.bookting.view.setting.SettingFragment


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    lateinit var tabs: Array<TabItem>
    var backPressTime = 0L

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
                val fragment = ShelfActivity()
                supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()

            }
            tabSearch -> {

            }
            tabSetting -> {
                val fragment = SettingFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
            }
            else -> {

            }
        }
    }

    override fun onBackPressed() {

        if (System.currentTimeMillis() > backPressTime + 3000) {
            backPressTime = System.currentTimeMillis()
            showToast(getString(R.string.back_press_exit))
            return
        }

        if (System.currentTimeMillis() <= backPressTime + 3000) {
            moveTaskToBack(true)
            finishAndRemoveTask()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}