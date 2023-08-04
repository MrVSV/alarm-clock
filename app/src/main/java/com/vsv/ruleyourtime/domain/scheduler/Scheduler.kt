package com.vsv.ruleyourtime.domain.scheduler

import com.vsv.ruleyourtime.data.local_db.AlarmItemEntity

interface Scheduler {

    fun schedule(entity: AlarmItemEntity)

    fun disable(entity: AlarmItemEntity)

    fun cancel(entity: AlarmItemEntity)
}