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

    val addAlreadyResponse: LiveData<ResultResponse>
        get() = _alreadyResponseData

    private val _alreadyResponseData = MutableLiveData<ResultResponse>()

    val addWishResponse: LiveData<ResultResponse>
        get() = _wishResponseData
    private val _wishResponseData = MutableLiveData<ResultResponse>()

    val shelfResponse: LiveData<ShelfResponse>
        get() = _shelfResponseData
    private val _shelfResponseData = MutableLiveData<ShelfResponse>()

    val BooktingHeader = mutableMapOf<String, String>()

    fun getHome() = repository.run {
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


    fun getBookDetail(bookId: Int) {
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

    fun addAlreadyBook(body: AlreadyBookItem) {
        repository.run {
            addAlreadyBook(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _alreadyResponseData.postValue(it)
                }
        }
    }

    fun addWishBook(body: WishBookData) {
        repository.run {
            addWishBook(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _wishResponseData.postValue(it)
                }
        }
    }

    fun getShelfByUser(month: String, page: Int? = 0) {
        BooktingHeader["access_token"] = "Bearer " + repository.sharedHelper.getAccessToken

        repository.run {
            getShelf(BooktingHeader.toMap(), month, page ?: 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _shelfResponseData.postValue(it)
                }
        }
    }
}