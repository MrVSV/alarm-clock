package com.vsv.core.domain.ringtone

interface RingtonePicker {

    suspend fun getRingtone(): MyRingtone

    suspend fun setRingtone(myRingtone: MyRingtone)

    suspend fun getRingtonesList(): List<MyRingtone>
}