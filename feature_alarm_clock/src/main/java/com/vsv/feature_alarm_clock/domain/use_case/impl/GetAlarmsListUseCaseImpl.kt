package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetAlarmsListUseCase
import kotlinx.coroutines.flow.Flow

class GetAlarmsListUseCaseImpl(
    private val repository: Repository,
) : GetAlarmsListUseCase {

    override operator fun invoke(): Flow<List<AlarmItem>> {
        return repository.getAlarmList()
    }
}