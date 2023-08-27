package com.vsv.core.domain.ringtone

import android.net.Uri

interface RingtonePicker {

    suspend fun getLastPickedRingtone(): MyRingtone

    suspend fun setRingtoneAsLastPicked(myRingtone: MyRingtone)

    suspend fun getRingtonesList(): List<MyRingtone>

    suspend fun addUserRingtone(uri: Uri): MyRingtone

//    suspend fun deleteUserRingtone(ringtone: MyRingtone): Boolean
}