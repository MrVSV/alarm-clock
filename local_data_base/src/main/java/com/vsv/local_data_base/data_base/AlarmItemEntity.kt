package com.vsv.local_data_base.data_base

import androidx.room.Entity
import androidx.room.PrimaryKey

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

}
