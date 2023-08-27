package com.vsv.feature_alarm_clock.domain.use_case.interfaces

import com.vsv.core.domain.ringtone.MyRingtone
import kotlinx.coroutines.flow.Flow

interface GetUserRingtonesListUseCase {
    operator fun invoke(): Flow<List<MyRingtone>>
}