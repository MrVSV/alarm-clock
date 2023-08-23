package com.vsv.feature_alarm_clock.domain.use_case.interfaces

import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import kotlinx.coroutines.flow.Flow

interface GetAlarmsListUseCase {
    operator fun invoke(): Flow<List<AlarmItem>>
}