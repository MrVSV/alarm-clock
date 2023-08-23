package com.vsv.feature_alarm_clock.utils

import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.model.toModel
import com.vsv.local_data_base.data_base.AlarmItemEntity
import com.vsv.local_data_base.data_base.UserRingtoneEntity

fun List<AlarmItemEntity>.toAlarmModelList(): List<AlarmItem> =
    this.map { entity -> entity.toModel() }

fun List<UserRingtoneEntity>.toMyRingtoneModelList(): List<MyRingtone> =
    this.map { entity -> entity.toModel() }

