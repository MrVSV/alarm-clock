package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DisableAlarmUseCase

class DisableAlarmUseCaseImpl(
    private val repository: Repository,
) : DisableAlarmUseCase {

    override suspend operator fun invoke(alarmItem: AlarmItem) {
        repository.disableAlarm(alarmItem.copy(isEnabled = false))
    }
}