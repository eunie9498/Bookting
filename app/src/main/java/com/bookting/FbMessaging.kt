package com.bookting

import com.bookting.data.MainConstants
import com.bookting.data.SharedHelper
import com.bookting.di.BookComponent
import com.bookting.di.BookNetworkModule
import com.bookting.di.DaggerBookComponent
import com.google.firebase.messaging.FirebaseMessagingService
import javax.inject.Inject

class FbMessaging: FirebaseMessagingService() {
    var component: BookComponent = DaggerBookComponent.builder()
        .bookNetworkModule(BookNetworkModule(this))
        .build()

    @Inject
    lateinit var sharedHelper: SharedHelper

    init {
        component.inject(this)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sharedHelper.addPreference(MainConstants.Shared.FB_TOKEN, token)
    }
}