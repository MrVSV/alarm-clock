package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import android.net.Uri
import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.model.AlarmItem

sealed interface RingtonePickerScreenEvent {
    class GetAlarmById(val alarmItemId: Int) : RingtonePickerScreenEvent
    class SetRingtone(val alarmItem: AlarmItem, val ringtone: MyRingtone) : RingtonePickerScreenEvent
    class AddUserRingtone(val uri: Uri): RingtonePickerScreenEvent
    class DeleteUserRingtone(val alarmItemId: Int, val ringtone: MyRingtone): RingtonePickerScreenEvent
}
