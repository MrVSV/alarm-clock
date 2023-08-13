package com.vsv.ruleyourtime.di

import com.vsv.feature_alarm_clock.data.repository.RepositoryImpl
import com.vsv.feature_alarm_clock.data.repository.UserPreferencesRepositoryImpl
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.repository.UserPreferencesRepository
import org.koin.dsl.module

val testRepositoryModule = module {

    single<Repository> { RepositoryImpl(alarmsDao = get(), alarmScheduler = get()) }

    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(dataStore = get()) }
}