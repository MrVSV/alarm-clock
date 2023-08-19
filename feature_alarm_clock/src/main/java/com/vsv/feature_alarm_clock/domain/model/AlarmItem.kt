package com.vsv.feature_alarm_clock.domain.model

import com.vsv.local_data_base.data_base.AlarmItemEntity


data class AlarmItem(
    val id: Int = 0,
    val isEnabled: Boolean,
    val hours: Int,
    val minutes: Int,
    val ringtoneTitle: String = "",
    val ringtoneUri: String = "",
) {

    fun toEntity() = AlarmItemEntity(
        id = id,
        isEnabled = isEnabled,
        hours = hours,
        minutes = minutes,
        ringtoneTitle = ringtoneTitle,
        ringtoneUri = ringtoneUri,
    )
}

fun AlarmItemEntity.toModel(): AlarmItem = AlarmItem(
    id = id,
    isEnabled = isEnabled,
    hours = hours,
    minutes = minutes,
    ringtoneTitle = ringtoneTitle,
    ringtoneUri = ringtoneUri,
)
