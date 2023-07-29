package com.vsv.ruleyourtime.data.repository

import com.vsv.ruleyourtime.data.alarmclock.AlarmItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addAlarm(alarmItem: AlarmItem)

    fun getAlarmList(): Flow<List<AlarmItem>>

    fun resetAllAlarms()

    suspend fun deleteAlarm(alarmItem: AlarmItem)

}