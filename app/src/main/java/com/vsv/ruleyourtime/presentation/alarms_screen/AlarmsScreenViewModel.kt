package com.vsv.ruleyourtime.presentation.alarms_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.ruleyourtime.data.alarm_clock.AlarmItem
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
                    repository.addAlarm(alarmItem = alarmItem)
                }
                _state.update {
                    it.copy(isAddingAlarm = false)
                }
            }
            is AlarmScreenEvent.DeleteAlarm -> {
                viewModelScope.launch {
                    repository.deleteAlarm(alarmItem = event.alarmItem)
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