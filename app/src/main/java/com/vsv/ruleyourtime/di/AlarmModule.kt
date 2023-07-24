package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.alarmclock.AlarmNotification
import com.vsv.ruleyourtime.alarmclock.AlarmScheduler
import com.vsv.ruleyourtime.alarmclock.AppNotification
import com.vsv.ruleyourtime.alarmclock.MyCalendar
import com.vsv.ruleyourtime.alarmclock.Scheduler
import org.koin.dsl.module

val alarmModule = module {

    factory<AppNotification> { AlarmNotification(context = get()) }

    single<Scheduler> { AlarmScheduler(context = get(), calendar = get()) }

    single { MyCalendar() }
}