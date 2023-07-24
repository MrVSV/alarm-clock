package com.vsv.ruleyourtime.alarmclock

import android.app.Notification

interface AppNotification {

    fun getNotification(itemId: Int): Notification
}