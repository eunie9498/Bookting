package com.bookting.di

import com.bookting.api.BookAPI
import com.bookting.models.MainConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class BookNetworkModule {
    @Provides
    @Singleton
    fun provideAPI(): BookAPI {
        return Retrofit.Builder().baseUrl(MainConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookAPI::class.java)
    }
}