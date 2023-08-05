package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.data.repository.RepositoryImpl
import com.vsv.ruleyourtime.data.repository.UserPreferencesRepositoryImpl
import com.vsv.ruleyourtime.domain.repository.Repository
import com.vsv.ruleyourtime.domain.repository.UserPreferencesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<Repository> { RepositoryImpl(alarmsDao = get(), alarmScheduler = get()) }

    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(dataStore = get()) }
}