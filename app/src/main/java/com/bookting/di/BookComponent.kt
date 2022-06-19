package com.bookting.di


import com.bookting.view.main.NetworkActivity
import dagger.Component
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