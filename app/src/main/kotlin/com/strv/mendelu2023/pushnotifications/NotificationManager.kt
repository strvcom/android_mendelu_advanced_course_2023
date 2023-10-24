package com.strv.mendelu2023.pushnotifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.net.HttpURLConnection
import java.net.URL

// TODO(5): create channel
fun Context.createNotificationChannel(channelId: String, channelName: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            enableVibration(true)
            enableLights(true)
            lightColor = Color.GREEN
        }

        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }
}

@SuppressLint("MissingPermission")
fun NotificationManagerCompat.sendNotification(
    pushNotification: PushNotification,
    context: Context,
) {
    if (pushNotification.name == null || pushNotification.message == null) return

    // TODO(8): pending intent to main activity
    val contentPendingIntent =
        PendingIntent.getActivity(
            context,
            0,
            MainActivity.newIntent(context),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

    // TODO(9): create notification
    val notification = NotificationCompat.Builder(
        context,
        context.getString(R.string.notification_channel_id_test)
    )
        .setSmallIcon(R.drawable.ic_message)
        .setContentTitle(pushNotification.name)
        .setContentText(pushNotification.message)
        .setLargeIcon(getBitmapFromURL(pushNotification.photoUrl))
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .build()
    // other options: actions, large icon, ongoing notification, style...

    // TODO(10): notify
    if (areNotificationsEnabled()) {
        notify(12, notification)
    }
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