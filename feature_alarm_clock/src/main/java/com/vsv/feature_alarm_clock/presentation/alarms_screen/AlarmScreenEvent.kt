package com.vsv.feature_alarm_clock.presentation.alarms_screen

import com.vsv.feature_alarm_clock.domain.model.AlarmItem

sealed interface AlarmScreenEvent{
    data class SetAlarmTime(val id: Int = 0, val hours: Int, val minutes: Int): AlarmScreenEvent
    data class ShowTimePicker(val alarmItem: AlarmItem?): AlarmScreenEvent
    object CloseTimePicker: AlarmScreenEvent
    data class DeleteAlarm(val alarmItem: AlarmItem): AlarmScreenEvent
    data class EnableAlarm(val alarmItem: AlarmItem): AlarmScreenEvent
    data class DisableAlarm(val alarmItem: AlarmItem): AlarmScreenEvent
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