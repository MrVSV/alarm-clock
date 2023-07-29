package com.vsv.ruleyourtime.presentation.alarmsscreen

import com.vsv.ruleyourtime.data.alarmclock.AlarmItem

data class AlarmsScreenState(
    val alarms: List<AlarmItem> = emptyList(),
    val hours: Int = 12,
    val minutes: Int = 0,
    val isAddingAlarm: Boolean = false
)