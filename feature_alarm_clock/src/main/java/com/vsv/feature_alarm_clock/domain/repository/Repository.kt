package com.vsv.feature_alarm_clock.domain.repository

import android.net.Uri
import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.core.domain.scheduler.ScheduleResult
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun scheduleAlarm(alarmItem: AlarmItem): ScheduleResult

    suspend fun updateAlarm(alarmItem: AlarmItem)

    suspend fun getAlarmById(alarmItemId: Int): AlarmItem

    fun getAlarmList(): Flow<List<AlarmItem>>

    fun resetAllAlarms()

    suspend fun deleteAlarm(alarmItem: AlarmItem)

    suspend fun disableAlarm(alarmItem: AlarmItem)

    suspend fun setRingtone(alarmItem: AlarmItem, myRingtone: MyRingtone)

    suspend fun getLastPickedRingtone(): MyRingtone

    suspend fun getDeviceRingtonesList(): List<MyRingtone>

    suspend fun addUserRingtone(uri: Uri)

//    suspend fun deleteUserRingtone(ringtone: MyRingtone)

    fun getUserRingtonesList(): Flow<List<MyRingtone>>

}