package com.bookting

import com.google.firebase.messaging.FirebaseMessagingService

class FbMessaging: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //todo token save

    }
}