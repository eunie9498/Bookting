package com.bookting.view.setting

import com.bookting.BaseActivity
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.databinding.SettingFragmentBinding
import com.bookting.utils.setImg

class SettingFragment : BaseFragment<SettingFragmentBinding>(R.layout.setting_fragment) {

    override fun SettingFragmentBinding.initView() {
        binding.fragment = this@SettingFragment
        binding.tvEmail.text = sharedHelper.getUserEmail
        binding.tvName.text = sharedHelper.getUserNick + "님"
        binding.imgProfile.setImg(sharedHelper.getUserProfile)
    }

    fun moveTo(type: Int) {
        when (type) {
            0 -> {
                (activity as BaseActivity<*>).moveToDetailAlready()
            }
            1 -> {

            }
        }
    }

    fun moveThreeDot() {
        (activity as BaseActivity<*>).moveToDot()
    }
}