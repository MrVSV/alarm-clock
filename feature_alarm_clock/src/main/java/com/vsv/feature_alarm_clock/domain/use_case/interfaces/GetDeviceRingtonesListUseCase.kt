package com.vsv.feature_alarm_clock.domain.use_case.interfaces

import com.vsv.core.domain.ringtone.MyRingtone

interface GetDeviceRingtonesListUseCase{
    suspend operator fun invoke(): List<MyRingtone>
}