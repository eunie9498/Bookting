package com.bookting.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookting.data.*
import com.bookting.repository.MainRepository
import com.bookting.utils.showToast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(val repository: MainRepository) : ViewModel() {

    val joinResponse = MutableLiveData<ResultResponse>()
    private val _joinResponse = MutableLiveData<ResultResponse>()

    val loginResponse = MutableLiveData<LoginResponse>()

    private val _homeResponse = MutableLiveData<HomeResponse>()

    val homeResponse: LiveData<HomeResponse>
        get() = _homeResponse

    private val _bookData = MutableLiveData<List<GetBookData>>()

    val bookData: LiveData<List<GetBookData>>
        get() = _bookData

    val detailData: LiveData<BookDetailItem>
        get() = _detailData

    private val _detailData = MutableLiveData<BookDetailItem>()

    val tagData: LiveData<List<TagItem>>
        get() = _tagData

    private val _tagData = MutableLiveData<List<TagItem>>()

    fun getHome() = repository.run {
        val BooktingHeader = mutableMapOf<String, String>()
        BooktingHeader["access_token"] = "Bearer " + sharedHelper.getAccessToken

        home(BooktingHeader.toMap()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    _homeResponse.postValue(response)
                }, { throwable ->
                    _homeResponse.postValue(HomeResponse("fail", throwable.message ?: "", null))
                })

    }

    fun join(body: JoinBody) {
        repository.run {
            join(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.result == MainConstants.SUCCESS) {
                        _joinResponse.postValue(it)
                    } else {
                        Log.d("체크해보자", it.reason ?: "")
                    }
                }
        }
    }

    fun bestSeller() {
        repository.run {
            bestSeller()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.result == MainConstants.SUCCESS) {
                        _bookData.postValue(it.data!!.toMutableList())
                    } else {
                        Log.d("체크해보자", it.reason!!)
                    }
                }
        }
    }

    fun newBooks() {
        repository.run {
            newBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.result == MainConstants.SUCCESS) {
                        _bookData.postValue(it.data!!.toMutableList())
                    } else {
                        Log.d("체크해보자", it.reason!!)
                    }
                }
        }
    }

    fun login(body: LoginBody) {
        repository.run {
            login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loginResponse.postValue(it)
                    if (it.result == MainConstants.SUCCESS) {
                        it.data?.let { data ->
                            repository.sharedHelper.addPreference(
                                MainConstants.Shared.REFRESH_TOKEN,
                                data.refresh_token
                            )
                            repository.sharedHelper.addPreference(
                                MainConstants.Shared.ACCESS_TOKEN,
                                data.access_token
                            )
                            loginResponse.postValue(it)
                        }
                    }
                }
        }
    }

    fun getBookDetail(bookId: Int) {
        val BooktingHeader = mutableMapOf<String, String>()
        BooktingHeader["access_token"] = "Bearer " + repository.sharedHelper.getAccessToken


        repository.run {
            getBookDetails(BooktingHeader.toMap(), bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.result == MainConstants.SUCCESS) {
                        _detailData.postValue(it.data!!)
                    } else {
                        Log.d("체크해보자", it.reason!!)
                    }
                }
        }
    }

    fun tag() {
        repository.run {
            tags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.result == MainConstants.SUCCESS) {
                        it.data?.let { arr ->
                            _tagData.postValue(arr)
                        }
                    }
                }
        }
    }
}