package com.bookting.di


import android.content.Context
import com.bookting.main.NetworkActivity
import dagger.BindsInstance
import dagger.Component
import javax.annotation.Nullable
import javax.inject.Singleton

@Component(modules = [BookNetworkModule::class])
@Singleton
interface BookComponent {
    fun inject(activity: NetworkActivity)
    @Component.Builder
    interface Builder {
        fun bookNetworkModule(module: BookNetworkModule) : Builder
        fun build() : BookComponent
    }
}