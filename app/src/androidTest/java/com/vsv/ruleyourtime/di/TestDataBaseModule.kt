package com.vsv.ruleyourtime.di

import androidx.room.Room
import com.vsv.local_data_base.data_base.AlarmsDataBase
import org.koin.dsl.module

val testDataBaseModule = module {

    single {
        Room.inMemoryDatabaseBuilder(
            context = get(),
            klass = AlarmsDataBase::class.java,
        ).build()
    }

    single { get<AlarmsDataBase>().alarmsDao() }
}