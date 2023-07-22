package com.vsv.ruleyourtime.alarmclock

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlarmItem(
    val id: Int,
    val alarmTimeMillis: Long,
    val isEnabled: Boolean,
) : Parcelable
