package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.AddUserRingtoneUseCase

class AddUserRingtoneUseCaseImpl(
    private val repository: Repository
): AddUserRingtoneUseCase {

    override suspend operator fun invoke(myRingtone: MyRingtone) {
        repository.addUserRingtone(myRingtone)
    }
}