package com.bookting.di


import com.bookting.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [BookNetworkModule::class])
@Singleton
interface BookComponent {
    fun inject(activity: MainActivity)
}