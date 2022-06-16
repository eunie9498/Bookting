package com.bookting.di

import com.bookting.api.BookAPI
import com.bookting.data.BooktingData
import com.bookting.data.MainConstants
import com.bookting.data.SharedHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class BookNetworkModule {
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
    abstract fun provideBookData(): BooktingData

    @Provides
    abstract fun getHelper(): SharedHelper
}