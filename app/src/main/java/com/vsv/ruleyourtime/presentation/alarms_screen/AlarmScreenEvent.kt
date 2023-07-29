package com.vsv.ruleyourtime.presentation.alarms_screen

import com.vsv.ruleyourtime.data.alarm_clock.AlarmItem

sealed interface AlarmScreenEvent{
    data class SetAlarmTime(val hours: Int, val minutes: Int): AlarmScreenEvent
    object ShowTimePicker: AlarmScreenEvent
    object CloseTimePicker: AlarmScreenEvent
    data class DeleteAlarm(val alarmItem: AlarmItem): AlarmScreenEvent
}