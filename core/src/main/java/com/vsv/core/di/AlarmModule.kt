package com.vsv.core.di

import com.vsv.core.data.AlarmNotification
import com.vsv.core.data.AlarmScheduler
import com.vsv.core.domain.AppNotification
import com.vsv.core.domain.Scheduler
import com.vsv.core.utils.MyCalendar
import org.koin.core.qualifier.named
import org.koin.dsl.module

val alarmModule = module {

    single<AppNotification>(
        qualifier = named("alarmNotification")
    ) {
        AlarmNotification(context = get())
    }

//    single<AppNotification>(qualifier = named("timerNotification")) { TimerNotification(context = get()) }

    single<Scheduler> { AlarmScheduler(context = get(), calendar = get()) }

    single { MyCalendar() }
}