package com.bookting.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val email: String? = "",
    val pw: String? = ""
) : Parcelable

