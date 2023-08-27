package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetAlarmByIdUseCase

class GetAlarmByIdUseCaseImpl(
    private val repository: Repository
): GetAlarmByIdUseCase {

    override suspend fun invoke(alarmId: Int): AlarmItem {
        return repository.getAlarmById(alarmId)
    }
}