package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.repository.Repository
import com.vsv.ruleyourtime.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<Repository> { RepositoryImpl(alarmsDao = get(), alarmScheduler = get()) }
}