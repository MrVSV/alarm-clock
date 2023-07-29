package com.vsv.ruleyourtime.data.local_db

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

    @Query("SELECT * FROM alarms ORDER BY hours ASC, minutes")
    fun getAlarmsList(): Flow<List<AlarmItemEntity>>

    @Query("SELECT * FROM alarms ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastUpdatedAlarm(): AlarmItemEntity

    @Query("SELECT * FROM alarms WHERE isEnabled=1")
    fun getAlarmsForReschedule(): List<AlarmItemEntity>
}