package com.bookting.view.setting

import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.databinding.SettingFragmentBinding
import com.bookting.utils.setImg

class SettingFragment : BaseFragment<SettingFragmentBinding>(R.layout.setting_fragment) {

    override fun SettingFragmentBinding.initView() {
        userViewModel.getUserData()
        userViewModel.userDataResponse.observe(this@SettingFragment) {
            it.data.let { data ->
                binding.tvEmail.text = data!!.email
                binding.tvName.text = "${data!!.nickname}님"
                binding.imgProfile.setImg(data!!.image_url ?: "")
            }

        }
    }
}