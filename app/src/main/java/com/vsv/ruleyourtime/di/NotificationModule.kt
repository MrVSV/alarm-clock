package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.notifications.AppNotification
import com.vsv.ruleyourtime.notifications.impl.AlarmNotification
import org.koin.core.qualifier.named
import org.koin.dsl.module

val notificationModule = module {

    single<AppNotification>(
        qualifier = named("alarmNotification")
    ) {
        AlarmNotification(context = get())
    }
}