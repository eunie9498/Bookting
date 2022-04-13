package com.bookting.api

import com.bookting.models.GetBookDetailResponse
import retrofit2.Call
import retrofit2.http.GET

interface BookAPI {
    @GET("books/{bookId}")
    fun getBooks(): Call<GetBookDetailResponse>
}