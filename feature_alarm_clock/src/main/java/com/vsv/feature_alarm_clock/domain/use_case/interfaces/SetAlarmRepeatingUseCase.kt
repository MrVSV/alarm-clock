package com.vsv.feature_alarm_clock.domain.use_case.interfaces

import com.vsv.feature_alarm_clock.domain.model.AlarmItem

interface SetAlarmRepeatingUseCase {
    suspend operator fun invoke(alarmItem: AlarmItem, dayIndex: Int)
}