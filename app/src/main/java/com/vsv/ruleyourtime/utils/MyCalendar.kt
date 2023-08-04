package com.vsv.ruleyourtime.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MyCalendar(private val context: Context) {

    fun convertToMillis(hour: Int, minute: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        if (calendar.timeInMillis < System.currentTimeMillis())
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        convertAlarmTime(calendar.timeInMillis)
        return calendar.timeInMillis
    }

    private fun convertAlarmTime(millis: Long) {
        val pattern = if (DateFormat.is24HourFormat(context)) "dd HH:mm" else "dd hh:mm a"
        Log.d(TAG, "convertAlarmTime:  ${SimpleDateFormat(pattern, Locale.getDefault()).format(System.currentTimeMillis())}")

        Log.d(TAG, "convertAlarmTime:  ${SimpleDateFormat(pattern, Locale.getDefault()).format(millis)}")
    }


}