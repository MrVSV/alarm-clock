package com.vsv.feature_alarm_clock.domain.model

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.local_data_base.data_base.AlarmItemEntity


data class AlarmItem(
    val id: Int = 0,
    val isEnabled: Boolean = true,
    val hours: Int = 0,
    val minutes: Int = 0,
    val ringtone: MyRingtone = MyRingtone("", "", false),
) {

    fun toEntity() = AlarmItemEntity(
        id = id,
        isEnabled = isEnabled,
        hours = hours,
        minutes = minutes,
        ringtoneTitle = ringtone.title,
        ringtoneUri = ringtone.uri,
    )
}

fun AlarmItemEntity.toModel(): AlarmItem = AlarmItem(
    id = id,
    isEnabled = isEnabled,
    hours = hours,
    minutes = minutes,
    ringtone = MyRingtone(
        uri = ringtoneUri,
        title = ringtoneTitle,
    ),
)
