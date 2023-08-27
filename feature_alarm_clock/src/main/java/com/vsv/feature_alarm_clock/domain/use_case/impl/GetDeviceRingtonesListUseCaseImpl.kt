package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetDeviceRingtonesListUseCase

class GetDeviceRingtonesListUseCaseImpl(
    private val repository: Repository
) : GetDeviceRingtonesListUseCase {
    override suspend fun invoke(): List<MyRingtone> {
       return repository.getDeviceRingtonesList()
    }
}