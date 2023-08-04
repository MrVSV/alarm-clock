package com.vsv.ruleyourtime.app

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.vsv.ruleyourtime.di.alarmModule
import com.vsv.ruleyourtime.di.dataBaseModule
import com.vsv.ruleyourtime.di.repositoryModule
import com.vsv.ruleyourtime.di.viewModelModule
import com.vsv.ruleyourtime.domain.notification.AppNotification
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named

class App : Application() {

    private val alarmNotification: AppNotification by inject(qualifier = named("alarmNotification"))
//    private val timerNotification: AppNotification by inject(qualifier = named("timerNotification"))

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(androidContext = this@App)
            modules(alarmModule, dataBaseModule, repositoryModule, viewModelModule)
        }

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.areNotificationsEnabled()
        notificationManager.createNotificationChannelsCompat(
            listOf(
                alarmNotification.createNotificationChanel(),
//                timerNotification.createNotificationChanel()
            )
        )

    }
}