package com.vsv.feature_alarm_clock.domain.model

import com.vsv.core.domain.Item
import com.vsv.local_data_base.data_base.AlarmItemEntity


data class AlarmItem(
    override val id: Int = 0,
    val isEnabled: Boolean,
    override val hours: Int,
    override val minutes: Int
): Item {

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
