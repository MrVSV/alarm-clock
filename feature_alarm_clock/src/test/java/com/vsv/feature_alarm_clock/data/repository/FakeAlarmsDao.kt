package com.vsv.feature_alarm_clock.data.repository

import com.vsv.local_data_base.data_base.AlarmItemEntity
import com.vsv.local_data_base.data_base.AlarmsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAlarmsDao : AlarmsDao {

    private val alarmsList = mutableListOf<AlarmItemEntity>()

    override suspend fun addAlarm(alarmItemEntity: AlarmItemEntity) {
        alarmsList.add(alarmItemEntity)
    }

    override suspend fun deleteAlarm(alarmItemEntity: AlarmItemEntity) {
        alarmsList.remove(alarmItemEntity)
    }

    override fun getAlarmsList(): Flow<List<AlarmItemEntity>> {
        return flow { emit(alarmsList) }
    }

    override suspend fun getLastUpdatedAlarm(): AlarmItemEntity {
        return alarmsList.last()
    }

    override fun getAlarmsForReschedule(): List<AlarmItemEntity> {
        return alarmsList
    }
}