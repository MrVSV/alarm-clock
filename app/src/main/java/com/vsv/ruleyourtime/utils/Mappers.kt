package com.vsv.ruleyourtime.utils

import com.vsv.ruleyourtime.data.alarm_clock.AlarmItem
import com.vsv.ruleyourtime.data.local_db.AlarmItemEntity

fun List<AlarmItemEntity>.toListModel(): List<AlarmItem> =
    this.map { entity -> entity.toModel() }

