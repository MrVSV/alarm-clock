package com.vsv.ruleyourtime.repository

import com.vsv.ruleyourtime.alarmclock.AlarmItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addAlarm(alarmItem: AlarmItem)

    fun getAlarmList(): Flow<List<AlarmItem>>

    fun resetAllAlarms()

}