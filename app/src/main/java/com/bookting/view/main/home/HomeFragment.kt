package com.bookting.view.main.home

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookting.BaseActivity
import com.bookting.BaseFragment
import com.bookting.R
import com.bookting.data.HOME
import com.bookting.data.MainConstants
import com.bookting.data.HOME.Recomm
import com.bookting.data.LoginBody
import com.bookting.databinding.FragmentHomeBinding
import com.bookting.view.main.MainActivity
import com.bookting.view.main.StartActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), HomeListener {

    override fun FragmentHomeBinding.initView() = with(binding) {

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HomeAdapter(this@HomeFragment)

        viewModel.getHome()
        viewModel.homeResponse.observe(requireActivity()) {
            if (it.result == MainConstants.SUCCESS) {
                (recyclerView.adapter as HomeAdapter).addItems(HOME.Nick(it.data!!.nickname))
                (recyclerView.adapter as HomeAdapter).addBadge(HOME.Badge)
                it.data!!.best_seller?.let { best->
                    (recyclerView.adapter as HomeAdapter).addRecomm(Recomm(best))
                }

            } else {
                if (it.reason!!.contains("access_token")) {
                    (activity as BaseActivity<*>).showBtnOneDlg(
                        getString(R.string.login_err_title),
                        getString(R.string.login_err_msg_expire), getString(R.string.ok)
                    ) {
                        val i = Intent(requireActivity(), StartActivity::class.java)
                        i.addFlags(FLAG_ACTIVITY_NO_HISTORY)
                        startActivity(i)
                    }
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
