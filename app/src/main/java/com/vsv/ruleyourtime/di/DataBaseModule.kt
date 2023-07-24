package com.vsv.ruleyourtime.di

import androidx.room.Room
import com.vsv.ruleyourtime.localdb.AlarmsDao
import com.vsv.ruleyourtime.localdb.AlarmsDataBase
import org.koin.dsl.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(
            context = get(),
            klass = AlarmsDataBase::class.java,
            name = "db"
        ).build()
    }

    single<AlarmsDao> {
        get<AlarmsDataBase>().alarmsDao()
    }
}