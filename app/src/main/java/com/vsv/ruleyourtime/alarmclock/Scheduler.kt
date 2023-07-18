package com.vsv.ruleyourtime.alarmclock

interface Scheduler {

    fun schedule(item: AlarmItem)

    fun disable(item: AlarmItem)

    fun cancel(item: AlarmItem)
}