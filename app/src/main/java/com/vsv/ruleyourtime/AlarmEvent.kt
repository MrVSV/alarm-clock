package com.vsv.ruleyourtime

import com.vsv.ruleyourtime.alarmclock.AlarmItem

sealed interface AlarmEvent{
//    object AddAlarm: AlarmEvent
    data class SetAlarmTime(val hours: Int, val minutes: Int): AlarmEvent
    object ShowTimePicker: AlarmEvent
    object CloseTimePicker: AlarmEvent
    data class DeleteAlarm(val alarmItem: AlarmItem): AlarmEvent
}