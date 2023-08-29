package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.SetAlarmRepeatingUseCase

class SetAlarmRepeatingUseCaseImpl(
    private val repository: Repository
): SetAlarmRepeatingUseCase {
    override suspend fun invoke(alarmItem: AlarmItem, dayIndex: Int) {
        val newAlarmDays = alarmItem.alarmDays.toMutableList()
        newAlarmDays[dayIndex] = !newAlarmDays[dayIndex]
        repository.updateAlarm(
            alarmItem.copy(
                alarmDays = newAlarmDays.toList()
            )
        )
    }
}