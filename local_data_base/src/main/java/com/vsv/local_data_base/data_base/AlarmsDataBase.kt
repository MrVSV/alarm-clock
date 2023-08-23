package com.vsv.local_data_base.data_base

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AlarmItemEntity::class, UserRingtoneEntity::class],
    version = 1,
)
abstract class AlarmsDataBase: RoomDatabase() {
    abstract fun alarmsDao(): AlarmsDao
    abstract fun userRingtoneDao(): UserRingtoneDao
}