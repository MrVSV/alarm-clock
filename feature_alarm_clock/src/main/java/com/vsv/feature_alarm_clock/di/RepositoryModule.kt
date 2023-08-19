package com.vsv.feature_alarm_clock.di

import com.vsv.feature_alarm_clock.data.repository.RepositoryImpl
import com.vsv.feature_alarm_clock.domain.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {

    single<Repository> { RepositoryImpl(alarmsDao = get(), alarmScheduler = get()) }

}