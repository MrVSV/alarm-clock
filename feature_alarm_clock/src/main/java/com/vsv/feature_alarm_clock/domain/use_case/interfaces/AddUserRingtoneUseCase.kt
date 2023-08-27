package com.vsv.feature_alarm_clock.domain.use_case.interfaces

import android.net.Uri

interface AddUserRingtoneUseCase {

    suspend operator fun invoke(uri: Uri)
}