package com.bookting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bookting.data.MainConstants
import com.bookting.repository.MainRepository
import com.bookting.ui.DlgOneBtn
import com.bookting.ui.ToastOneBtn
import com.bookting.utils.setImg
import com.bookting.view.detail.DetailActivity
import com.bookting.view.detail.DetailAlreadyActivity
import com.bookting.view.main.MainActivity
import com.bookting.view.main.NetworkActivity
import com.bookting.view.main.best.BestActivity
import com.bookting.view.main.new.NewActivity
import com.bookting.view.setting.dot.ThreeDotActivity
import com.bookting.viewmodel.MainViewModel
import com.bookting.viewmodel.UserViewModel

open class BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : NetworkActivity() {

    lateinit var binding: T
    lateinit var userViewModel: UserViewModel
    lateinit var viewModel: MainViewModel
    lateinit var repository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        val factory = ViewModelFactory(MainRepository(this))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        repository = MainRepository(this)
        binding.onCreate()
    }


    inner class ViewModelFactory(val repository: MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
            MainViewModel::class.java -> MainViewModel(MainRepository(this@BaseActivity))
            UserViewModel::class.java -> UserViewModel(MainRepository(this@BaseActivity))
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }


    open fun T.onCreate() = Unit

    fun showToast(toast: String) {
        Toast.makeText(binding.root.context, toast, Toast.LENGTH_SHORT).show()
    }


    fun showToastOneBtn(
        title: String,
        msg: String,
        btnTxt: String,
        btnListener: View.OnClickListener
    ) {
        val onebtn = ToastOneBtn(this@BaseActivity)
        onebtn.apply {
            setTitle(title)
            setMsg(msg)
            setBtn(btnTxt) {
                btnListener
                dismiss()
            }
        }
        onebtn.show()
    }

    fun moveToMain() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    fun moveToBestAct() {
        val i = Intent(this, BestActivity::class.java)
        startActivity(i)
    }

    fun moveToNew() {
        val i = Intent(this, NewActivity::class.java)
        startActivity(i)
    }

    fun moveToDetail(bookId: Int) {
        val i = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(MainConstants.BUNDLE_KEY.BOOK_ID, bookId)
        i.putExtras(bundle)
        startActivity(i)
    }

    fun moveToDetailAlready() {
        val i = Intent(this, DetailAlreadyActivity::class.java)
        startActivity(i)
    }

    fun moveToDot() {
        val i = Intent(this, ThreeDotActivity::class.java)
        startActivity(i)
    }

    fun showBtnOneDlg(
        title: String,
        msg: String,
        btnTxt: String,
        listener: View.OnClickListener? = null
    ) {
        val dlg = DlgOneBtn(this)
        dlg.apply {
            setTitle(title)
            setMsg(msg)
            setListener(btnTxt, listener ?: null)
            show()
        }

    }
}