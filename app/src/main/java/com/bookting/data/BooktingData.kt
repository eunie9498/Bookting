package com.bookting.data

import javax.inject.Inject

class BooktingData {
    @Inject
    lateinit var helper: SharedHelper

    val getToken: String
        get() = helper.getSharedPreferences().getString(MainConstants.Shared.TOKEN, "") ?: ""
}