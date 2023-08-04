package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.data.alarm_clock.foreground_services.AlarmNotification
import com.vsv.ruleyourtime.data.alarm_clock.scheduler.AlarmScheduler
import com.vsv.ruleyourtime.domain.notification.AppNotification
import com.vsv.ruleyourtime.domain.scheduler.Scheduler
import com.vsv.ruleyourtime.utils.MyCalendar
import org.koin.core.qualifier.named
import org.koin.dsl.module

val alarmModule = module {

    single<AppNotification>(qualifier = named("alarmNotification")) { AlarmNotification(context = get()) }

//    single<AppNotification>(qualifier = named("timerNotification")) { TimerNotification(context = get()) }

    single<Scheduler> { AlarmScheduler(context = get(), calendar = get()) }

    single { MyCalendar(context = get()) }
}