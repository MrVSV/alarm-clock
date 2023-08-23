package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import com.vsv.core.domain.ringtone.MyRingtone

sealed interface RingtonePickerScreenEvent {
    class SetRingtone(val alarmItemId: Int, val ringtone: MyRingtone) : RingtonePickerScreenEvent
    class AddUserRingtone(val myRingtone: MyRingtone): RingtonePickerScreenEvent
}
