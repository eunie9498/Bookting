package com.bookting.di


import com.bookting.main.NetworkActivity
import dagger.Component
import javax.annotation.Nullable
import javax.inject.Singleton

@Component(modules = [BookNetworkModule::class])
@Singleton
interface BookComponent {
    fun inject(activity: NetworkActivity)
}