package com.vsv.ruleyourtime.data.alarm_clock

import android.app.Notification

interface AppNotification {

    fun getNotification(itemId: Int): Notification
}