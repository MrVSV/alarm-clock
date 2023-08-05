package com.vsv.feature_alarm_clock.di

import com.vsv.feature_alarm_clock.data.alarm_clock.scheduler.AlarmScheduler
import com.vsv.feature_alarm_clock.domain.alarm_clock.AppNotification
import com.vsv.feature_alarm_clock.domain.alarm_clock.Scheduler
import com.vsv.feature_alarm_clock.utils.MyCalendar
import org.koin.core.qualifier.named
import org.koin.dsl.module

val alarmModule = module {

    single<AppNotification>(qualifier = named("alarmNotification")) {
        com.vsv.feature_alarm_clock.data.alarm_clock.foreground_services.AlarmNotification(
            context = get()
        )
    }

//    single<AppNotification>(qualifier = named("timerNotification")) { TimerNotification(context = get()) }

    single<Scheduler> { AlarmScheduler(context = get(), calendar = get()) }

    single { MyCalendar() }
}