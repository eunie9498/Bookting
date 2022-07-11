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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
class BookNetworkModule(val context: Context) {

    @Provides
    fun providecontext() : Context = context

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedHelper{
        return SharedHelper(context)
    }

    @Provides
    @Singleton
    fun provideAPI(): BookAPI {
        return Retrofit.Builder().baseUrl(MainConstants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(AppInterceptor(provideSharedPreferences())).build())
            .build()
            .create(BookAPI::class.java)
    }
    class AppInterceptor(val sharedHelper: SharedHelper) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain)
                : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization", "Bearer " + sharedHelper.getRefreshToken)
                .build()

            proceed(newRequest)
        }
    }

}