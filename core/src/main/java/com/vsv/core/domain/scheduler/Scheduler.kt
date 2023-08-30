package com.vsv.core.domain.scheduler

import com.vsv.core.domain.model.Item


interface Scheduler {

    fun schedule(item: Item, alarmDays: List<Boolean>): ScheduleResult

    fun disable(item: Item)

    fun cancel(item: Item)
}