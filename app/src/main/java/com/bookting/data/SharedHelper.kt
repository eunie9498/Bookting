package com.bookting.data

import android.content.Context
import android.content.SharedPreferences

object SharedHelper {
    private const val PREF_MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(MainConstants.SHARED, PREF_MODE)
    }

    fun getSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }

    fun addShared(key: String, value: Any?) {
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
}