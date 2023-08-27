package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DeleteUserRingtoneUseCase

class DeleteUserRingtoneUseCaseImpl(
    private val repository: Repository,
) : DeleteUserRingtoneUseCase {

    override suspend fun invoke(myRingtone: MyRingtone) {
//        repository.deleteUserRingtone(myRingtone)
    }
}