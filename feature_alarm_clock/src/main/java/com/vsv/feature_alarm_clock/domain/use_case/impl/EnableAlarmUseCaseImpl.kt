package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.EnableAlarmUseCase

class EnableAlarmUseCaseImpl(
    private val repository: Repository,
) : EnableAlarmUseCase {

    override suspend operator fun invoke(alarmItem: AlarmItem) {
        repository.scheduleAlarm(alarmItem.copy(isEnabled = true))
    }
}