package com.vsv.ruleyourtime.presentation.alarms_screen

import com.vsv.ruleyourtime.data.alarm_clock.AlarmItem

data class AlarmsScreenState(
    val alarms: List<AlarmItem> = emptyList(),
    val hours: Int = 12,
    val minutes: Int = 0,
    val isAddingAlarm: Boolean = false
)