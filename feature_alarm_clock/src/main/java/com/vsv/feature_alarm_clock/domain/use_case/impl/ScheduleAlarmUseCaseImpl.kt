package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.scheduler.ScheduleResult
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.ScheduleAlarmUseCase

class ScheduleAlarmUseCaseImpl(
    private val repository: Repository
) : ScheduleAlarmUseCase {
    override suspend operator fun invoke(alarmItem: AlarmItem): ScheduleResult {
       return repository.scheduleAlarm(alarmItem)
    }
}