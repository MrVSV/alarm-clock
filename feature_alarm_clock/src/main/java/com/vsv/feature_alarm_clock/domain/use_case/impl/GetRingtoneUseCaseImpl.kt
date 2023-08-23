package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetRingtoneUseCase

class GetRingtoneUseCaseImpl(
    private val repository: Repository
): GetRingtoneUseCase {
    override suspend fun invoke(): MyRingtone {
        return repository.getRingtone()
    }
}