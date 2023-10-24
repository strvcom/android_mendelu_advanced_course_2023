package com.strv.mendelu2023.pushnotifications

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import java.net.HttpURLConnection
import java.net.URL

// TODO(5): create channel

@SuppressLint("MissingPermission")
fun NotificationManagerCompat.sendNotification(
    pushNotification: PushNotification,
    context: Context,
) {
    if (pushNotification.name == null || pushNotification.message == null) return

    // TODO(8): pending intent to main activity

    // TODO(9): create notification

    // TODO(10): notify

}

private fun getBitmapFromURL(src: String?): Bitmap? {
    return try {
        val input = with(URL(src).openConnection() as HttpURLConnection) {
            doInput = true
            connect()
            inputStream
        }
        BitmapFactory.decodeStream(input)
    } catch (e: Exception) {
        Log.e("MENDELU", "Bitmap download failed with error: ${e.message}")
        null
    }
}