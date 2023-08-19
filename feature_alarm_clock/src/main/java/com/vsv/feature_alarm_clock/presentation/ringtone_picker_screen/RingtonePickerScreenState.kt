package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import com.vsv.core.domain.MyRingtone
import com.vsv.feature_alarm_clock.domain.model.AlarmItem

data class RingtonePickerScreenState (
    val alarmItem: AlarmItem? = null,
    val ringtonesList: List<MyRingtone> = emptyList(),
    val defaultRingtone: MyRingtone? = null,
    val lastPickedRingtoneUri: String? = null
) {

}
