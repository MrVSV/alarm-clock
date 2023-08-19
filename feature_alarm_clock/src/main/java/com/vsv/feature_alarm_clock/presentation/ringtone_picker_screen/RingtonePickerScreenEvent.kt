package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import com.vsv.core.domain.MyRingtone

sealed interface RingtonePickerScreenEvent {
    class SetRingtone(val alarmItemId: Int, val ringtone: MyRingtone) : RingtonePickerScreenEvent
}
