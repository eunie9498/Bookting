package com.bookting.api

import com.bookting.data.GetBookDetailResponse
import com.bookting.data.JoinBody
import com.bookting.data.ResultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookAPI {
    @GET("books/{bookId}")
    fun getBooks(): Call<GetBookDetailResponse>

    @POST("users")
    fun joinUser(@Body body: JoinBody): Call<ResultResponse>
}