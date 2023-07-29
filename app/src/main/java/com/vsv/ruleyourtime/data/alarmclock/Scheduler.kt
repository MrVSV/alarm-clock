package com.vsv.ruleyourtime.data.alarmclock

import com.vsv.ruleyourtime.data.localdb.AlarmItemEntity

interface Scheduler {

    fun schedule(entity: AlarmItemEntity)

    fun disable(entity: AlarmItemEntity)

    fun cancel(entity: AlarmItemEntity)
}