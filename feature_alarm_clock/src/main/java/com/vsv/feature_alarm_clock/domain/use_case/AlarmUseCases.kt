package com.vsv.feature_alarm_clock.domain.use_case

import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DeleteAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DisableAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.EnableAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetAlarmByIdUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetAlarmsListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.ScheduleAlarmUseCase

data class AlarmUseCases(
    val scheduleAlarmUseCase: ScheduleAlarmUseCase,
    val deleteAlarmUseCase: DeleteAlarmUseCase,
    val enableAlarmUseCase: EnableAlarmUseCase,
    val disableAlarmUseCase: DisableAlarmUseCase,
    val getAlarmsListUseCase: GetAlarmsListUseCase,
    val getAlarmByIdUseCase: GetAlarmByIdUseCase
)
