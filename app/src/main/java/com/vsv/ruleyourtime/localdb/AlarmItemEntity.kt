package com.vsv.ruleyourtime.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vsv.ruleyourtime.alarmclock.AlarmItem

@Entity(tableName = "alarms")
data class AlarmItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
//    val alarmTimeMillis: Long,
    val isEnabled: Boolean,
    val hours: Int,
    val minutes: Int,
    val timeStamp: Long = System.currentTimeMillis()
){
    fun toModel() = AlarmItem(
        id = id,
        isEnabled = isEnabled,
        hours = hours,
        minutes = minutes,
        )
}
