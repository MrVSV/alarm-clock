package com.vsv.feature_alarm_clock.domain.model

import com.vsv.local_data_base.data_base.AlarmItemEntity


data class AlarmItem(
    val id: Int = 0,
    val isEnabled: Boolean,
    val hours: Int,
    val minutes: Int
) {

    fun toEntity() = AlarmItemEntity(
        id = id,
        isEnabled = isEnabled,
        hours = hours,
        minutes = minutes,
        )
}

fun AlarmItemEntity.toModel(): AlarmItem = AlarmItem(
    id = id,
    isEnabled = isEnabled,
    hours = hours,
    minutes = minutes,
)
