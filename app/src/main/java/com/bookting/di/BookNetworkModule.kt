package com.bookting.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.bookting.api.BookAPI
import com.bookting.data.MainConstants
import com.bookting.data.SharedHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class BookNetworkModule(val context: Context) {

    @Provides
    fun providecontext() : Context = context

    @Provides
    @Singleton
    fun provideAPI(): BookAPI {
        return Retrofit.Builder().baseUrl(MainConstants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedHelper{
        return SharedHelper(context)
    }

}