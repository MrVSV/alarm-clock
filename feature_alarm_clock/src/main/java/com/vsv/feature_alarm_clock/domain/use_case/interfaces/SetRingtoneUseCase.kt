package com.vsv.feature_alarm_clock.domain.use_case.interfaces

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.model.AlarmItem

interface SetRingtoneUseCase {
    suspend operator fun invoke(alarmItem: AlarmItem, myRingtone: MyRingtone)
}