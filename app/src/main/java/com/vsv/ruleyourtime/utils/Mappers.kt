package com.vsv.ruleyourtime.utils

import com.vsv.ruleyourtime.alarmclock.AlarmItem
import com.vsv.ruleyourtime.localdb.AlarmItemEntity

fun List<AlarmItemEntity>.toListModel(): List<AlarmItem> =
    this.map { entity -> entity.toModel() }

