package com.vsv.ruleyourtime.domain.model

import android.os.Parcelable
import com.vsv.ruleyourtime.data.local_db.AlarmItemEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlarmItem(
    val id: Int = 0,
//    val alarmTimeMillis: Long,
    val isEnabled: Boolean,
    val hours: Int,
    val minutes: Int
) : Parcelable{

    fun toEntity() = AlarmItemEntity(
        id = id,
        isEnabled = isEnabled,
        hours = hours,
        minutes = minutes,


        )
}
