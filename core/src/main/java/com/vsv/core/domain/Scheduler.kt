package com.vsv.core.domain



interface Scheduler {

    fun schedule(item: Item): ScheduleResult

    fun disable(item: Item)

    fun cancel(item: Item)
}