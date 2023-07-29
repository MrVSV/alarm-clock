package com.vsv.ruleyourtime.data.alarmclock

import android.app.Notification

interface AppNotification {

    fun getNotification(itemId: Int): Notification
}