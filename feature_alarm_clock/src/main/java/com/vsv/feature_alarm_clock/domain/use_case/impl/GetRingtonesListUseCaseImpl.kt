package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetRingtonesListUseCase

class GetRingtonesListUseCaseImpl(
    private val repository: Repository
) : GetRingtonesListUseCase {
    override suspend fun invoke(): List<MyRingtone> {
       return repository.getRingtonesList()
    }
}