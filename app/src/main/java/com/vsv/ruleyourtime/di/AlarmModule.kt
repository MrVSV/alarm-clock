package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.data.alarm_clock.AlarmNotification
import com.vsv.ruleyourtime.data.alarm_clock.AlarmScheduler
import com.vsv.ruleyourtime.data.alarm_clock.AppNotification
import com.vsv.ruleyourtime.data.alarm_clock.MyCalendar
import com.vsv.ruleyourtime.data.alarm_clock.Scheduler
import org.koin.dsl.module

val alarmModule = module {

    factory<AppNotification> { AlarmNotification(context = get()) }

    single<Scheduler> { AlarmScheduler(context = get(), calendar = get()) }

    single { MyCalendar(context = get()) }
}