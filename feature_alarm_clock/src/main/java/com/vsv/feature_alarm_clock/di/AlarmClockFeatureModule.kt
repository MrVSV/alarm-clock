package com.vsv.feature_alarm_clock.di

import org.koin.dsl.module

val alarmClockFeatureModule = module {
    includes(
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}