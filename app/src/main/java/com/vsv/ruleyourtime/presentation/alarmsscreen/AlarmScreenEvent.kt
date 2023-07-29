package com.vsv.ruleyourtime.presentation.alarmsscreen

import com.vsv.ruleyourtime.data.alarmclock.AlarmItem

sealed interface AlarmScreenEvent{
    data class SetAlarmTime(val hours: Int, val minutes: Int): AlarmScreenEvent
    object ShowTimePicker: AlarmScreenEvent
    object CloseTimePicker: AlarmScreenEvent
    data class DeleteAlarm(val alarmItem: AlarmItem): AlarmScreenEvent
}