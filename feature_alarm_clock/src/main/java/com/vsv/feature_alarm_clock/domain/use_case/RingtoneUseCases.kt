package com.vsv.feature_alarm_clock.domain.use_case

import com.vsv.feature_alarm_clock.domain.use_case.interfaces.AddUserRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetRingtonesListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.SetRingtoneUseCase

data class RingtoneUseCases(
    val getRingtoneUseCase: GetRingtoneUseCase,
    val setRingtoneUseCase: SetRingtoneUseCase,
    val getRingtonesListUseCase: GetRingtonesListUseCase,
    val addUserRingtoneUseCase: AddUserRingtoneUseCase
) {
}