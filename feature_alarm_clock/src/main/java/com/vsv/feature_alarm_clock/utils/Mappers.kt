package com.vsv.feature_alarm_clock.utils

import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.model.toModel
import com.vsv.local_data_base.data_base.AlarmItemEntity

fun List<AlarmItemEntity>.toListModel(): List<AlarmItem> =
    this.map { entity -> entity.toModel() }

