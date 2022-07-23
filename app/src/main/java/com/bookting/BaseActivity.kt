package com.bookting

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bookting.repository.MainRepository
import com.bookting.ui.ToastOneBtn
import com.bookting.view.main.MainActivity
import com.bookting.view.main.NetworkActivity
import com.bookting.view.main.best.BestActivity
import com.bookting.view.main.new.NewActivity
import com.bookting.viewmodel.MainViewModel

open class BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : NetworkActivity() {

    lateinit var binding: T
    lateinit var viewModel: MainViewModel
    lateinit var repository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        val factory = ViewModelFactory(MainRepository(this))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        repository = MainRepository(this)

        binding.onCreate()
    }

    inner class ViewModelFactory(val repository: MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(MainRepository::class.java).newInstance(repository)
        }
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

    fun moveToNew(){
        val i = Intent(this, NewActivity::class.java)
        startActivity(i)
    }
}