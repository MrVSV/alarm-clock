package com.vsv.ruleyourtime.utils

import com.vsv.ruleyourtime.data.local_db.AlarmItemEntity
import com.vsv.ruleyourtime.domain.model.AlarmItem

fun List<AlarmItemEntity>.toListModel(): List<AlarmItem> =
    this.map { entity -> entity.toModel() }

