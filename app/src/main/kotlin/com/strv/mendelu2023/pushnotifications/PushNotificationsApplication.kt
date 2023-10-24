package com.strv.mendelu2023.pushnotifications

import android.app.Application

class PushNotificationsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // TODO(6): create channel
        createNotificationChannel(
            channelId = getString(R.string.notification_channel_id_test),
            channelName = getString(R.string.notification_channel_name_test),
        )
    }
}