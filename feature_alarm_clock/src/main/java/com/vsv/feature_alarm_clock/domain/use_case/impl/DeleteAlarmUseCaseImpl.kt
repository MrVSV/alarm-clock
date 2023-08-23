package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DeleteAlarmUseCase

class DeleteAlarmUseCaseImpl(
    private val repository: Repository,
) : DeleteAlarmUseCase {

    override suspend operator fun invoke(alarmItem: AlarmItem) {
        repository.deleteAlarm(alarmItem)
    }
}