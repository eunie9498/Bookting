package com.bookting.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val email: String? = "",
    val pw: String? = ""
) : Parcelable

@Parcelize
data class JoinBody(
    var birth_date: String?="",
    var email: String?="",
    var name: String?="",
    var password: String?="",
    var secret_key: String?="",
    var sex: String?=""
): Parcelable

@Parcelize
data class ResultResponse(
    var result: String?="",
    var reason: String?=""
): Parcelable

@Parcelize
data class GetBookDetailResponse(
    val result: String? = "",
    val reason: String? = "",
    val data: BookItem?=null
): Parcelable

@Parcelize
data class BookItem(
    val author: String,
    val detail_info: String,
    val id: Int,
    val image_url: String,
    val isbn: String,
    val name: String,
    val price: Int,
    val publication_date: String,
    val publisher: String
) : Parcelable