package com.vsv.ruleyourtime

import android.app.Application
import android.app.NotificationManager
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import com.vsv.ruleyourtime.alarmclock.AlarmNotification

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChanel()
    }

    private fun createNotificationChanel() {
        val channel = NotificationChannelCompat.Builder(
            AlarmNotification.AlARM_CHANNEL_ID,
            NotificationManager.IMPORTANCE_MAX
        )
            .setName("Active Alarms")
            .setSound(null, null)
            .setVibrationEnabled(true)
            .build()

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.createNotificationChannel(channel)
    }
}