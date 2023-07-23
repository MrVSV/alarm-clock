package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.alarmclock.AlarmNotification
import com.vsv.ruleyourtime.alarmclock.AppNotification
import org.koin.dsl.module

val alarmModule = module {

    factory<AppNotification> { AlarmNotification(context = get()) }
}