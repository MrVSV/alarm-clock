package com.vsv.ruleyourtime

import android.app.Application
import android.app.NotificationManager
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import com.vsv.ruleyourtime.alarmclock.AlarmNotification
import com.vsv.ruleyourtime.di.alarmModule
import com.vsv.ruleyourtime.di.dataBaseModule
import com.vsv.ruleyourtime.di.repositoryModule
import com.vsv.ruleyourtime.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(androidContext = this@App)
            modules(alarmModule, dataBaseModule, repositoryModule, viewModelModule)
        }
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