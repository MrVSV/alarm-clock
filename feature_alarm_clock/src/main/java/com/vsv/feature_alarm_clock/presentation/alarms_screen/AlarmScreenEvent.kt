package com.vsv.feature_alarm_clock.presentation.alarms_screen

import com.vsv.core.utils.Event
import com.vsv.feature_alarm_clock.domain.model.AlarmItem

sealed interface AlarmScreenEvent : Event {
    class SetAlarmTime(val id: Int = 0, val hours: Int, val minutes: Int) : AlarmScreenEvent
    class ShowTimePicker(val alarmItem: AlarmItem?) : AlarmScreenEvent
    object CloseTimePicker : AlarmScreenEvent
    class DeleteAlarm(val alarmItem: AlarmItem) : AlarmScreenEvent
    class EnableAlarm(val alarmItem: AlarmItem) : AlarmScreenEvent
    class DisableAlarm(val alarmItem: AlarmItem) : AlarmScreenEvent
    object OnSnackbarClose : AlarmScreenEvent
    object ShowAlarmRationale : AlarmScreenEvent
    object ShowNotificationRationale : AlarmScreenEvent
    object CloseAlarmRationale : AlarmScreenEvent
    object CloseNotificationRationale : AlarmScreenEvent
    class CheckTimeFormat(val is24HourFormat: Boolean) : AlarmScreenEvent
    object ChangeTimePickerInputMode : AlarmScreenEvent
}