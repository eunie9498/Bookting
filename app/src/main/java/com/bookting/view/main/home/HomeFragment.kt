package com.bookting.view.main.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.bookting.BaseActivity
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.HOME
import com.bookting.data.MainConstants
import com.bookting.data.HOME.Recomm
import com.bookting.data.LoginBody
import com.bookting.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), HomeListener {

    override fun FragmentHomeBinding.initView() = with(binding) {

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HomeAdapter(this@HomeFragment)

        viewModel.getHome()

        viewModel.homeResponse.observe(requireActivity()) {
            if (it.result == MainConstants.SUCCESS) {
                (recyclerView.adapter as HomeAdapter).addItems(HOME.Nick(it.data.nickname))
                (recyclerView.adapter as HomeAdapter).addBadge(HOME.Badge)
                (recyclerView.adapter as HomeAdapter).addRecomm(Recomm(it.data.best_seller))
            } else {
                if (it.reason!!.contains("access_token")) {
                    (activity as BaseActivity<*>).showBtnOneDlg(
                        getString(R.string.login_err_title),
                        getString(R.string.login_err_msg_expire), getString(R.string.ok)
                    )
                }
            }
        }

    }

    override fun onBest() {
        (requireActivity() as BaseActivity<*>).moveToBestAct()
    }

    override fun onNew() {
        (requireActivity() as BaseActivity<*>).moveToNew()
    }

    override fun onRecommend(bookId: Int) {
        (requireActivity() as BaseActivity<*>).moveToDetail(bookId)
    }

}
