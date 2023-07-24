package com.vsv.ruleyourtime.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class AlarmItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val alarmTimeMillis: Long,
    val isEnabled: Boolean,
)
