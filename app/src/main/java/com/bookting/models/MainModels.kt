package com.bookting.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val email: String? = "",
    val pw: String? = ""
) : Parcelable

@Parcelize
data class GetBookDetailResponse(
    val result: String? = "",
    val reason: String? = "",
    val data: BookItem
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