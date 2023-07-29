package com.vsv.ruleyourtime.data.alarm_clock

import com.vsv.ruleyourtime.data.local_db.AlarmItemEntity

interface Scheduler {

    fun schedule(entity: AlarmItemEntity)

    fun disable(entity: AlarmItemEntity)

    fun cancel(entity: AlarmItemEntity)
}