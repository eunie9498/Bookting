package com.bookting.di


import com.bookting.FbMessaging
import com.bookting.repository.MainRepository
import com.bookting.view.main.NetworkActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [BookNetworkModule::class])
@Singleton
interface BookComponent {
    fun inject(activity: NetworkActivity)
    fun inject(repository: MainRepository)
    fun inject(service: FbMessaging)

    @Component.Builder
    interface Builder {
        fun bookNetworkModule(module: BookNetworkModule) : Builder
        fun build() : BookComponent
    }
}