package com.vsv.ruleyourtime.utils

import com.vsv.ruleyourtime.data.alarmclock.AlarmItem
import com.vsv.ruleyourtime.data.localdb.AlarmItemEntity

fun List<AlarmItemEntity>.toListModel(): List<AlarmItem> =
    this.map { entity -> entity.toModel() }

