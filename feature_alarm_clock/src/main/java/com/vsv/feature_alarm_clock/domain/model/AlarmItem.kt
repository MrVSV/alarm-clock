package com.vsv.feature_alarm_clock.domain.model

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.local_data_base.data_base.AlarmItemEntity


data class AlarmItem(
    val id: Int = 0,
    val isEnabled: Boolean = true,
    val hours: Int = 0,
    val minutes: Int = 0,
    val ringtone: MyRingtone = MyRingtone("", "", false),
    val alarmDays: List<Boolean> = List(7) { false },
) {

    fun isRepeating() = alarmDays.contains(true)

    fun toEntity() = AlarmItemEntity(
        id = id,
        isEnabled = isEnabled,
        hours = hours,
        minutes = minutes,
        ringtoneTitle = ringtone.title,
        ringtoneUri = ringtone.uri,
        monday = alarmDays[0],
        tuesday = alarmDays[1],
        wednesday = alarmDays[2],
        thursday = alarmDays[3],
        friday = alarmDays[4],
        saturday = alarmDays[5],
        sunday = alarmDays[6],
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
    alarmDays = listOf(
        monday, tuesday, wednesday, thursday, friday, saturday, sunday
    )
)
