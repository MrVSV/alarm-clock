package com.vsv.ruleyourtime.presentation.alarmsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.ruleyourtime.data.alarmclock.AlarmItem
import com.vsv.ruleyourtime.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlarmsScreenViewModel(
    private val repository: Repository,
//    private val myCalendar: MyCalendar
) : ViewModel() {

    private val _alarms = repository.getAlarmList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
    private val _state = MutableStateFlow(AlarmsScreenState())
    val state = combine(_state, _alarms) { state, alarms ->
        state.copy(alarms = alarms)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = AlarmsScreenState()
    )

    fun onEvent(event: AlarmScreenEvent) {
        when (event) {
            AlarmScreenEvent.ShowTimePicker -> {
                val currentTime = System.currentTimeMillis()
//                myCalendar.
                _state.update {
                    it.copy(isAddingAlarm = true)
                }
            }
            AlarmScreenEvent.CloseTimePicker -> {
                _state.update {
                    it.copy(isAddingAlarm = false)
                }
            }
            is AlarmScreenEvent.SetAlarmTime -> {
                val alarmItem = AlarmItem(
                    hours = event.hours,
                    minutes = event.minutes,
                    isEnabled = true
                )
                viewModelScope.launch {
                    repository.addAlarm(alarmItem)
                }
                _state.update {
                    it.copy(isAddingAlarm = false)
                }
            }
//            AlarmScreenEvent.AddAlarm -> {
//                val hours = state.value.hours
//                val minutes = state.value.minutes
//                val alarmItem = AlarmItem(
//                    hours = hours,
//                    minutes = minutes,
//                    isEnabled = true
//                )
//            }
            is AlarmScreenEvent.DeleteAlarm -> {
                viewModelScope.launch {
                    repository.deleteAlarm(event.alarmItem)
                }
            }
        }
    }


    fun addAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch {
            repository.addAlarm(alarmItem = alarmItem)
        }
    }
}