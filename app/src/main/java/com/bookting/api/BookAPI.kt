package com.bookting.api

import com.bookting.data.GetBookDetailResponse
import com.bookting.data.JoinBody
import com.bookting.data.ResultResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookAPI {
    @GET("books/{bookId}")
    fun getBooks(): Observable<GetBookDetailResponse>

    @POST("users")
     fun joinUser(@Body body: JoinBody): Observable<ResultResponse>
}