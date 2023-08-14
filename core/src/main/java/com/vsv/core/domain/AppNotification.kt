package com.vsv.core.domain

import android.app.Notification
import androidx.core.app.NotificationChannelCompat

interface AppNotification {

    fun createNotificationChanel(): NotificationChannelCompat

    fun getNotification(itemId: Int): Notification
}