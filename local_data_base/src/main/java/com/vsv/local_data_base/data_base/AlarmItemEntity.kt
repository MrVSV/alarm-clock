package com.vsv.local_data_base.data_base

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vsv.core.domain.model.Item

@Entity(tableName = "alarms")
data class AlarmItemEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    val isEnabled: Boolean,
    override val hours: Int,
    override val minutes: Int,
    val timeStamp: Long = System.currentTimeMillis(),
    val ringtoneTitle: String,
    val ringtoneUri: String
): Item
