package com.vsv.feature_alarm_clock.domain.use_case.impl

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetUserRingtonesListUseCase
import kotlinx.coroutines.flow.Flow

class GetUserRingtonesListUseCaseImpl(
    private val repository: Repository
): GetUserRingtonesListUseCase {
    override fun invoke(): Flow<List<MyRingtone>> {
        return repository.getUserRingtonesList()
    }
}