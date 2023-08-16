package com.vsv.ruleyourtime.notifications

import android.app.Notification
import androidx.core.app.NotificationChannelCompat

interface AppNotification {

    fun createNotificationChanel(): NotificationChannelCompat

    fun createNotification(itemId: Int): Notification
}