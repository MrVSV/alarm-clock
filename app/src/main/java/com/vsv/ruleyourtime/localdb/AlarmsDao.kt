package com.vsv.ruleyourtime.localdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmsDao {

    @Upsert
    suspend fun addAlarm(alarmItemEntity: AlarmItemEntity)

    @Delete
    suspend fun deleteAlarm(alarmItemEntity: AlarmItemEntity)

    @Query("SELECT * FROM alarms")
    fun getAlarmsList(): Flow<List<AlarmItemEntity>>
}