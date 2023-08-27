package com.vsv.feature_alarm_clock.domain.use_case

import com.vsv.feature_alarm_clock.domain.use_case.interfaces.AddUserRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DeleteUserRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetDeviceRingtonesListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetLastPickedRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetUserRingtonesListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.SetRingtoneUseCase

data class RingtoneUseCases(
    val getLastPickedRingtoneUseCase: GetLastPickedRingtoneUseCase,
    val setRingtoneUseCase: SetRingtoneUseCase,
    val getDeviceRingtonesListUseCase: GetDeviceRingtonesListUseCase,
    val getUserRingtonesListUseCase: GetUserRingtonesListUseCase,
    val addUserRingtoneUseCase: AddUserRingtoneUseCase,
    val deleteUserRingtoneUseCase: DeleteUserRingtoneUseCase,
) {
}