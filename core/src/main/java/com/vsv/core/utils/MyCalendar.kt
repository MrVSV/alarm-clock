package com.vsv.core.utils

import java.util.Calendar

class MyCalendar {

    /**Возможно костыли, но пока не смог придумать другой алгоритм*/
    fun convertToMillis(hour: Int, minute: Int, alarmDays: List<Boolean>): Long {
        val calendar = Calendar.getInstance()
        /**Календарь начинает неделю с воскресенья. Для удобства меняем начало на понедельник*/
        val today =
            if (calendar.get(Calendar.DAY_OF_WEEK) == 1) 7
            else calendar.get(Calendar.DAY_OF_WEEK) - 1
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        if (alarmDays.contains(true)) {
            /**Если время будильника меньше, чем текущее,
             * то отсчет нужно начинать со следующего дня.
             * Если больше, то с сегодняшнего*/
            var i =
                if (calendar.timeInMillis < System.currentTimeMillis())
                        if (today == 7) 1 else today + 1
                else today
            repeat(7) {
                if (!alarmDays[i - 1]) {
                    i = if (i == 7) 1 else i + 1
                } else return@repeat
            }
            val daysToAdd = if (i < today) i - today + 7 else i - today
            if (daysToAdd == 0) {
                if (calendar.timeInMillis < System.currentTimeMillis()) {
                    calendar.add(Calendar.DAY_OF_WEEK, 7)
                }
                else {
                    calendar.add(Calendar.DAY_OF_WEEK, daysToAdd)
                }
            } else {
                calendar.add(Calendar.DAY_OF_WEEK, daysToAdd)
            }
        } else {
            if (calendar.timeInMillis < System.currentTimeMillis())
                calendar.add(Calendar.DAY_OF_WEEK, 1)
        }
        return calendar.timeInMillis
    }
}