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
    var email: String? = "",
    var nickname: String? = "",
    var password: String? = "",
    var secret_key: String? = "",
    var sex: String? = ""
) : Parcelable

@Parcelize
data class LoginBody(
    var email: String? = "",
    var password: String? = "",
    var secret_key: String? = "",
) : Parcelable

@Parcelize
data class ResultResponse(
    var result: String? = "",
    var reason: String? = ""
) : Parcelable

@Parcelize
data class LoginResponse(
    var result: String? = "",
    var reason: String? = "",
    var data: TokenData? = null
) : Parcelable

@Parcelize
data class TokenData(
    var access_token: String? = "",
    var refresh_token: String? = ""
) : Parcelable

@Parcelize
data class GetBookDetailResponse(
    val result: String? = "",
    val reason: String? = "",
    val data: BookDetailItem? = null
) : Parcelable

@Parcelize
data class BookDetailItem(
    val author: List<String>,
    val detail_info: String,
    val id: Int,
    val image_url: String,
    val isbn: String,
    val name: String,
    val price: Int,
    val publication_date: String,
    val publisher: String
) : Parcelable

@Parcelize
data class TagResponse(
    var result: String,
    var reason: String? = "",
    var data: List<TagItem>? = null
) : Parcelable

@Parcelize
data class TagItem(
    var id: Int,
    var item: String
) : Parcelable

@Parcelize
data class SearchBookResponse(
    var result: String,
    var reason: String? = "",
    var data: List<SearchBookItem>
) : Parcelable

@Parcelize
data class SearchBookItem(
    var authors: String,
    var id: Int,
    var name: String,
    var publisher: String
) : Parcelable

@Parcelize
data class WishBookResponse(
    var result: String?,
    var reason: String?,
    var data: List<WishBookData>
) : Parcelable

@Parcelize
data class WishBookData(
    var book_id: Int,
    var memo: String
) : Parcelable

@Parcelize
data class MemoBody(
    var memo: String
) : Parcelable

@Parcelize
data class AlreadyBookItem(
    var book_id: Int,
    var memo: String,
    var rate: Int,
    var tag_ids: List<Int>
) : Parcelable

@Parcelize
data class GetAlreadyBookResponse(
    var result: String?,
    var reason: String?,
    var data: List<GetAlreadyBookItem>
) : Parcelable

@Parcelize
data class GetAlreadyBookItem(
    var book_name: String,
    var id: Int
) : Parcelable

@Parcelize
data class EditAlreadyBookBody(
    var memo: String,
    var rate: Int,
    var tag_ids: List<Int>
) : Parcelable