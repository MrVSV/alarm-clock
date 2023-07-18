package com.vsv.ruleyourtime

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.vsv.ruleyourtime.alarmclock.NotificationService

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChanel()
    }

    private fun createNotificationChanel() {
        val channel = NotificationChannel(
            NotificationService.AlARM_CHANNEL_ID,
            "Active Alarms",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}