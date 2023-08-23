package com.vsv.feature_alarm_clock.domain.use_case.interfaces

import com.vsv.core.domain.ringtone.MyRingtone

interface GetRingtoneUseCase {
    suspend operator fun invoke(): MyRingtone
}