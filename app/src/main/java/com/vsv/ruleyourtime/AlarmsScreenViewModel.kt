package com.vsv.ruleyourtime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.ruleyourtime.alarmclock.AlarmItem
import com.vsv.ruleyourtime.repository.Repository
import kotlinx.coroutines.launch

class AlarmsScreenViewModel(
    private val repository: Repository
): ViewModel() {

//    val item = mutableStateOf(AlarmItem(1, 0, true))
//
//    fun getTimeMillis(hour: Int, minute: Int) {
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.HOUR_OF_DAY, hour)
//        calendar.set(Calendar.MINUTE, minute)
//        calendar.set(Calendar.SECOND, 0)
//        calendar.set(Calendar.MILLISECOND, 0)
//        if(calendar.timeInMillis < System.currentTimeMillis()) {
//            Log.d(TAG, "getTimeMillis: ${calendar.timeInMillis}")
//            Log.d(TAG, "getTimeMillis: ${calendar.timeInMillis < System.currentTimeMillis()}")
//            calendar.add(Calendar.DAY_OF_MONTH, 1)
//            Log.d(TAG, "getTimeMillis: ${calendar.timeInMillis}")
//        }
//        item.value = AlarmItem(1, calendar.timeInMillis, true)
//    }

    fun addAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch {
            repository.addAlarm(alarmItem = alarmItem)
        }
    }
}