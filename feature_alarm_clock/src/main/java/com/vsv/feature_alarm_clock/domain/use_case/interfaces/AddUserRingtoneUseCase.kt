package com.vsv.feature_alarm_clock.domain.use_case.interfaces

import com.vsv.core.domain.ringtone.MyRingtone

interface AddUserRingtoneUseCase {

    suspend operator fun invoke(myRingtone: MyRingtone)
}