package com.bookting.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Base64
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

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

    val getFbToken: String
        get() = sharedPreferences.getString(MainConstants.Shared.FB_TOKEN, "") ?: ""

    val getAccessToken: String
        get() = sharedPreferences.getString(MainConstants.Shared.ACCESS_TOKEN, "") ?: ""

    val getRefreshToken: String
        get() = sharedPreferences.getString(MainConstants.Shared.REFRESH_TOKEN, "") ?: ""

    fun newEncrypt(
        input: ByteArray,
    ): String {
        return newEncryptUtil(Cipher.ENCRYPT_MODE, input)
    }

    fun newDecrypt(
        input: ByteArray,
    ): String {
        return newEncryptUtil(Cipher.DECRYPT_MODE, input)
    }

    private fun newEncryptUtil(mode: Int, input: ByteArray): String {
        try {

            val key = ("chaeking" + getFbToken).substring(0, 32).toByteArray(Charset.defaultCharset())
            val iv = getFbToken.substring(0, 16).toByteArray(Charset.defaultCharset())
            val secretKeySpec = SecretKeySpec(key, "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(mode, secretKeySpec, IvParameterSpec(iv))
            cipher.doFinal(input)
            val cipherText = cipher.doFinal(input)
            return Base64.encodeToString(cipherText, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }
}