package com.vsv.feature_alarm_clock.domain.repository

import com.vsv.core.domain.ScheduleResult
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addAlarm(alarmItem: AlarmItem): ScheduleResult

    fun getAlarmList(): Flow<List<AlarmItem>>

    fun resetAllAlarms()

    suspend fun deleteAlarm(alarmItem: AlarmItem)

    suspend fun disableAlarm(alarmItem: AlarmItem)

}