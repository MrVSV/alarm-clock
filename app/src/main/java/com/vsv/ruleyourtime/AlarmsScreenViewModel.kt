package com.vsv.ruleyourtime

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vsv.ruleyourtime.alarmclock.AlarmItem
import java.util.Calendar

class AlarmsScreenViewModel: ViewModel() {

    val item = mutableStateOf(AlarmItem(1, 0, true))

    fun getTimeMillis(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        if(calendar.timeInMillis < System.currentTimeMillis()) {
            Log.d(TAG, "getTimeMillis: ${calendar.timeInMillis}")
            Log.d(TAG, "getTimeMillis: ${calendar.timeInMillis < System.currentTimeMillis()}")
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            Log.d(TAG, "getTimeMillis: ${calendar.timeInMillis}")
        }
        item.value = AlarmItem(1, calendar.timeInMillis, true)
//        item.value = AlarmItem(2, calendar.timeInMillis + 15*1000L, true)
    }
}