package com.vsv.ruleyourtime.app

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.vsv.core.di.coreModule
import com.vsv.feature_alarm_clock.di.repositoryModule
import com.vsv.feature_alarm_clock.di.viewModelModule
import com.vsv.local_data_base.di.dataBaseModule
import com.vsv.ruleyourtime.di.notificationModule
import com.vsv.ruleyourtime.notifications.AppNotification
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
            modules(notificationModule, coreModule, dataBaseModule, repositoryModule, viewModelModule)
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