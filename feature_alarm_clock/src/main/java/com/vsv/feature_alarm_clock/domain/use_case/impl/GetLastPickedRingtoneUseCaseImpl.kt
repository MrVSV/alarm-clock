package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetLastPickedRingtoneUseCase

class GetLastPickedRingtoneUseCaseImpl(
    private val repository: Repository
): GetLastPickedRingtoneUseCase {
    override suspend fun invoke(): MyRingtone {
        return repository.getLastPickedRingtone()
    }
}