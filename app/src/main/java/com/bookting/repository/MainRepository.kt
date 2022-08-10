package com.bookting.repository

import android.content.Context
import android.util.Log
import com.bookting.BaseActivity
import com.bookting.R
import com.bookting.api.BookAPI
import com.bookting.data.*
import com.bookting.di.BookComponent
import com.bookting.di.BookNetworkModule
import com.bookting.di.DaggerBookComponent
import com.bookting.utils.showToast
import com.bookting.viewmodel.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Header
import java.io.IOException
import javax.inject.Inject

class MainRepository(context: Context) {
    var component: BookComponent = DaggerBookComponent.builder()
        .bookNetworkModule(BookNetworkModule(context))
        .build()

    @Inject
    lateinit var api: BookAPI

    @Inject
    lateinit var sharedHelper: SharedHelper

    init {
        component.inject(this)
    }

    fun join(body: JoinBody) = api.joinUser(body)

    fun home(header: Map<String, String>) = api.getHomeByUser(header)

    fun login(body: LoginBody) = api.login(body)

    fun bestSeller() = api.getBestSeller()

    fun newBooks() = api.getNewBook()

    fun getBookDetails(header: Map<String, String>, bookId: Int) =
        api.getBookDetails(header, bookId)

    fun tags() = api.getTags()

    fun addAlreadyBook(body: AlreadyBookItem) = api.AddAlreadyRead(body)

    fun addWishBook(body: WishBookData) = api.addWishBook(body)

    fun getUserData() = api.getUserData()

    fun getShelf(header: Map<String, String>, month: String, page: Int) =
        api.getBookShelf(header, month, page)
}