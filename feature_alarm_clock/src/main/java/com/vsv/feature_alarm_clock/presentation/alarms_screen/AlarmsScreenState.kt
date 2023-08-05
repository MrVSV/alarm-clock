package com.vsv.feature_alarm_clock.presentation.alarms_screen

import com.vsv.feature_alarm_clock.domain.model.AlarmItem

data class AlarmsScreenState(
    val alarms: List<AlarmItem> = emptyList(),
    val selectedAlarm : AlarmItem? = null,
    val hours: Int = 12,
    val minutes: Int = 0,
    val is24HourFormat: Boolean = true,
    val isAddingAlarm: Boolean = false,
    val isDialTimePickerInputMode: Boolean = true,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    /*alarm permission state*/
    val isAlarmsEnable: Boolean = false,
    val isShouldShowAlarmRationale: Boolean = false,
    val isAlarmRationaleShown: Boolean = false,
    /*notification permission state*/
    val isNotificationEnable: Boolean = false,
    val isShouldShowNotificationRationale: Boolean = false,
    val isNotificationRationaleShown: Boolean = false,
)