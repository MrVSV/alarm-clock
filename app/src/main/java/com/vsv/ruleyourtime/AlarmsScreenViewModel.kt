package com.vsv.ruleyourtime

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vsv.ruleyourtime.alarmclock.AlarmItem
import java.util.Calendar

class AlarmsScreenViewModel: ViewModel() {

    val item = mutableStateOf(AlarmItem(0, 0, true))

    fun getTimeMillis(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        item.value = AlarmItem(0, calendar.timeInMillis, true)
    }
}