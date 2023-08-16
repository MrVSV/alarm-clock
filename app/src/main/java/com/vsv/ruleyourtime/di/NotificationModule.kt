package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.notifications.impl.AlarmNotification
import com.vsv.ruleyourtime.notifications.AppNotification
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(KoinInternalApi::class)
val notificationModule = module {

    single<AppNotification>(
        qualifier = named("alarmNotification")
    ) {
        AlarmNotification(context = get())
    }
}