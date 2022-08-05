package com.bookting.data

import android.os.Parcelable
import com.bookting.view.main.home.HomeBadge
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val email: String? = "",
    val pw: String? = ""
) : Parcelable

@Parcelize
data class HomeResponse(
    var result: String? = "",
    var reason: String? = "",
    var data: HomeData? = null
) : Parcelable

@Parcelize
data class HomeData(
    var best_seller: List<HomeBestSeller>? = null,
    var book_analysis: HomeBookContents,
    var nickname: String? = ""
) : Parcelable

@Parcelize
data class HomeBookContents(
    var contents: List<HOME.HomeBookContent>,
    var total_amount: Int,
    var type: String
) : Parcelable

sealed class HOME {
    @Parcelize
    data class HomeBookContent(
        var amount: Int,
        var name: String,
    ) : Parcelable, HOME()

    @Parcelize
    data class Nick(
        var nickName: String?
    ) : Parcelable, HOME()

    object Badge : HOME()

    @Parcelize
    data class HomeGraph(
        var name: String,
        var amt: Int
    ) : Parcelable, HOME()

    @Parcelize
    data class Recomm(
        val data: List<HomeBestSeller>? = null
    ) : Parcelable, HOME()
}


@Parcelize
data class BookData(
    val authors: String? = "",
    var id: Int,
    var image_url: String? = "",
    var name: String? = "",
) : Parcelable

@Parcelize
data class HomeBestSeller(
    var authors: String? = "",
    var id: Int? = 0,
    var image_url: String? = "",
    var name: String? = "",
    var publisher: String? = ""
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
data class GetBookResponse(
    val result: String? = "",
    val reason: String? = "",
    val data: List<GetBookData>? = null
) : Parcelable

@Parcelize
data class GetBookData(
    val authors: String? = "",
    var id: Int? = 0,
    var image_url: String? = "",
    var name: String? = "",
    var publisher: String? = ""
) : Parcelable

@Parcelize
data class BookDetailItem(
    val authors: List<String>,
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
    var name: String,
    var selected: Boolean? = false
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

@Parcelize
data class UserData(
    var email: String,
    var image_url: String? = "",
    var nickname: String,
    var night_push: Boolean? = false,
    var push: Boolean? = false
) : Parcelable

@Parcelize
data class UserDataResponse(
    var result: String,
    var reason: String? = "",
    var data: UserData
) : Parcelable