package com.vsv.core.domain

interface RingtonePicker {

    fun getRingtone(): MyRingtone

    fun setRingtone(myRingtone: MyRingtone)

    fun getRingtonesList(): List<MyRingtone>
}