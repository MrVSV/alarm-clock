package com.vsv.ruleyourtime.localdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AlarmItemEntity::class],
    version = 1,
)
abstract class AlarmsDataBase: RoomDatabase() {
    abstract fun alarmsDao(): AlarmsDao
}