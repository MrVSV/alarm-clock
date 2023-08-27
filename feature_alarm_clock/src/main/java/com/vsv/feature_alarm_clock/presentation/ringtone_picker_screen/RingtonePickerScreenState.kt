package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.model.AlarmItem

data class RingtonePickerScreenState (
    val alarmItem: AlarmItem = AlarmItem(),
    val hasItemBeenReceived: Boolean = false,
    val ringtoneList: List<MyRingtone> = emptyList(),
    val userRingtoneHasBeenDeleted: Boolean = false
)
