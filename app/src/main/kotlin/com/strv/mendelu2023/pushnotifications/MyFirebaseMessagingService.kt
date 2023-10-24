package com.strv.mendelu2023.pushnotifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.squareup.moshi.Moshi
import org.json.JSONObject

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendToServer(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // TODO(12): handle notification
    }

    private fun pushNotification(data: Map<String, String>): PushNotification? {
        val moshi = Moshi.Builder().build()
        return moshi.adapter(PushNotification::class.java).fromJson(JSONObject(data).toString())
    }

    private fun sendToServer(token: String) {
        Log.d("MENDELU", "sendToServer: $token")
        // TODO send token to your backend
    }
}