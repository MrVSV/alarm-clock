package com.vsv.ruleyourtime.di

import androidx.room.Room
import com.vsv.ruleyourtime.data.local_db.AlarmsDataBase
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