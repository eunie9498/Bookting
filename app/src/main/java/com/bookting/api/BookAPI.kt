package com.bookting.api

import com.bookting.data.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface BookAPI {

    @GET("home")
    fun getHomeByUser(
        @HeaderMap header: Map<String, String>
    ): Observable<HomeResponse>

    @GET("books/{book_id}")
    fun getBookDetails(
        @HeaderMap header: Map<String, String>,
        @Path("book_id") book_id:Int): Observable<GetBookDetailResponse>

    @POST("auth/join")
    fun joinUser(@Body body: JoinBody): Observable<ResultResponse>

    @POST("auth/token")
    fun login(@Body body: LoginBody): Observable<LoginResponse>

    @GET("tags")
    fun getTags(): Observable<TagResponse>

    @GET("books")
    fun searchBooks(
        @Query("query") query: String,
        @Query("target") target: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Observable<SearchBookResponse>

    //읽고싶은책
    @POST("book-memories/wish")
    fun wishBook(
        @Body body: WishBookData
    ): Observable<ResultResponse>

    @GET("book-memories/wish")
    fun getWishBook(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Observable<WishBookResponse>

    @PUT("book-memories/wish/{book_memory_wish_id}")
    fun editWishBook(
        @Path("book_memory_wish_id") book_memory_wish_id: Int,
        @Body body: MemoBody
    ): Observable<ResultResponse>

    @DELETE("book-memories/wish/{book_memory_wish_id}")
    fun deleteWishBook(
        @Path("book_memory_wish_id") book_memory_wish_id: Int
    ): Observable<ResultResponse>

    //이미 읽은
    @POST("book-memories/complete")
    fun AddAlreadyRead(
        @Body body: AlreadyBookItem
    ): Observable<ResultResponse>

    @GET("book-memories/complete")
    fun GetAlreadyRead(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Observable<GetAlreadyBookResponse>

    @PUT("book-memories/complete/{book_memory_complete_id}")
    fun EditAlreadyRead(
        @Path("book_memory_complete_id") book_memory_complete_id: Int,
        @Body body: EditAlreadyBookBody
    ): Observable<ResultResponse>

    @DELETE("book-memories/complete/{book_memory_complete_id}")
    fun DeleteAlreadyRead(
        @Path("book_memory_complete_id") book_memory_complete_id: Int,
    ): Observable<ResultResponse>

    @GET("best-sellers")
    fun getBestSeller(): Observable<GetBookResponse>

    @GET("new-books")
    fun getNewBook(): Observable<GetBookResponse>


}