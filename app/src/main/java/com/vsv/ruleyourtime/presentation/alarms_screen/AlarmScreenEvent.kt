package com.vsv.ruleyourtime.presentation.alarms_screen

import com.vsv.ruleyourtime.domain.model.AlarmItem

sealed interface AlarmScreenEvent{
    data class SetAlarmTime(val hours: Int, val minutes: Int): AlarmScreenEvent
    object ShowTimePicker: AlarmScreenEvent
    object CloseTimePicker: AlarmScreenEvent
    data class DeleteAlarm(val alarmItem: AlarmItem): AlarmScreenEvent
    object OnSnackbarClose: AlarmScreenEvent
    object ShowAlarmRationale: AlarmScreenEvent
    object ShowNotificationRationale: AlarmScreenEvent
    object CloseAlarmRationale: AlarmScreenEvent
    object CloseNotificationRationale: AlarmScreenEvent
    data class CheckAlarmPermissionState(val isGranted: Boolean): AlarmScreenEvent
    data class CheckNotificationPermissionState(val isGranted: Boolean): AlarmScreenEvent
    data class CheckTimeFormat(val is24HourFormat: Boolean): AlarmScreenEvent
    object ChangeTimePickerInputMode: AlarmScreenEvent
}