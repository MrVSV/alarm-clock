package com.vsv.core.domain

import android.app.Notification
import androidx.core.app.NotificationChannelCompat

interface AppNotification {

    fun isNotificationEnabled(): Boolean

    fun createNotificationChanel(): NotificationChannelCompat

    fun getNotification(itemId: Int): Notification
}