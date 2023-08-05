package com.vsv.local_data_base.di

import androidx.room.Room
import com.vsv.local_data_base.data_base.AlarmsDataBase
import org.koin.dsl.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(
            context = get(),
            klass = AlarmsDataBase::class.java,
            name = "db"
        ).build()
    }

    single { get<AlarmsDataBase>().alarmsDao() }
}