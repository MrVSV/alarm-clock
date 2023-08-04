package com.vsv.ruleyourtime.presentation.alarms_screen

import com.vsv.ruleyourtime.domain.model.AlarmItem

data class AlarmsScreenState(
    val alarms: List<AlarmItem> = emptyList(),
    val hours: Int = 12,
    val minutes: Int = 0,
    val isAddingAlarm: Boolean = false,
    val isNotificationEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isShouldShowAlarmRationale: Boolean = false,
    val isShouldShowNotificationRationale: Boolean = false
)