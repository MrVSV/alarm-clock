package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.SetRingtoneUseCase

class SetRingtoneUseCaseImpl(
    private val repository: Repository
): SetRingtoneUseCase {
    override suspend fun invoke(alarmItemId: Int, myRingtone: MyRingtone) {
        repository.setRingtone(alarmItemId, myRingtone)
    }
}