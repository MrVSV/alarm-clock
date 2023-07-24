package com.vsv.ruleyourtime.alarmclock

import java.util.Calendar

class MyCalendar {

    private val calendar = Calendar.getInstance()

    fun convertToMillis(hour: Int, minute: Int): Long {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        if (calendar.timeInMillis < System.currentTimeMillis())
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        return calendar.timeInMillis
    }
}