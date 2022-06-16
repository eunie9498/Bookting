package com.bookting.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedHelper(context: Context) {

    var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(MainConstants.SHARED, Context.MODE_PRIVATE)

    fun addPreference(key: String, value: Any?) {
        sharedPreferences.edit().apply {
            when (value) {
                is Int -> putInt(key, value).apply()
                is Boolean -> putBoolean(key, value).apply()
                is String -> putString(key, value).apply()
                is Float -> putFloat(key, value).apply()
                is Long -> putLong(key, value).apply()
                else -> return
            }
        }
    }

    val getToken: String
        get() = sharedPreferences.getString(MainConstants.Shared.TOKEN, "") ?: ""
}