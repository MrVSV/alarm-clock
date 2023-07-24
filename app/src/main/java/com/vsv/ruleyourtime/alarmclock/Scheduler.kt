package com.vsv.ruleyourtime.alarmclock

import com.vsv.ruleyourtime.localdb.AlarmItemEntity

interface Scheduler {

    fun schedule(entity: AlarmItemEntity)

    fun disable(/*item: AlarmItem*/)

    fun cancel(entity: AlarmItemEntity)
}