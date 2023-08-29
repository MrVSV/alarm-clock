package com.vsv.feature_alarm_clock.presentation.alarms_screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.core.domain.permission_state.RationaleState
import com.vsv.core.domain.permission_state.RationaleStateRepository
import com.vsv.core.domain.scheduler.ScheduleResult
import com.vsv.core.domain.use_cases.RationaleStateUseCases
import com.vsv.core.utils.Event
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.use_case.AlarmUseCases
import com.vsv.feature_alarm_clock.domain.use_case.RingtoneUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime

class AlarmsScreenViewModel(
    private val alarmUseCases: AlarmUseCases,
    private val rationaleStateUseCases: RationaleStateUseCases,
    private val ringtoneUseCases: RingtoneUseCases,
) : ViewModel() {

    private val _alarms = alarmUseCases.getAlarmsListUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    private val _rationaleState = rationaleStateUseCases.getRationaleStatesUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RationaleState()
    )

    private val _state = MutableStateFlow(AlarmsScreenState())
    val state =
        combine(_state, _alarms, _rationaleState) { state, alarms, prefs ->
            state.copy(
                alarms = alarms,
                hours = if (LocalTime.now().hour < 23) LocalTime.now().hour + 1 else 0,
                minutes = LocalTime.now().minute,
                isAlarmRationaleShown = prefs.isAlarmRationaleShown,
                isNotificationRationaleShown = prefs.isNotificationRationaleShown
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = AlarmsScreenState()
        )

    fun onEvent(event: Event) {
        when (event) {
            is AlarmScreenEvent.ShowTimePicker -> {
                _state.update {
                    it.copy(
                        isAddingAlarm = true,
                        selectedAlarm = event.alarmItem
                    )
                }
            }
            AlarmScreenEvent.CloseTimePicker -> {
                _state.update {
                    it.copy(isAddingAlarm = false)
                }
            }
            is AlarmScreenEvent.SetAlarmTime -> {
                viewModelScope.launch {
                    val alarmItem = event.alarmItem?.copy(
                        hours = event.hours,
                        minutes = event.minutes
                    ) ?: AlarmItem(
                        id = 0,
                        hours = event.hours,
                        minutes = event.minutes,
                        isEnabled = true,
                        ringtone = ringtoneUseCases.getLastPickedRingtoneUseCase()
                    )
                    when (alarmUseCases.scheduleAlarmUseCase(alarmItem = alarmItem)) {
                        ScheduleResult.Success -> {
                            _state.update {
                                it.copy(isAddingAlarm = false, selectedAlarm = null)
                            }
                        }
                        is ScheduleResult.Error -> {
                            Log.d(TAG, "onEvent: error")
                            _state.update {
                                it.copy(isAddingAlarm = false, selectedAlarm = null)
                            }
                        }
                    }
                }
            }
            is AlarmScreenEvent.DeleteAlarm -> {
                viewModelScope.launch {
                    alarmUseCases.deleteAlarmUseCase(alarmItem = event.alarmItem)
                }
            }
            AlarmScreenEvent.OnSnackbarClose -> {
                _state.update {
                    it.copy(isError = false)
                }
            }
            AlarmScreenEvent.CloseAlarmRationale -> {
                viewModelScope.launch {
                    rationaleStateUseCases.saveRationaleStatesUseCase(
                        RationaleStateRepository.IS_ALARM_RATIONALE_SHOWN,
                        true
                    )
                }
                _state.update {
                    it.copy(isShouldShowAlarmRationale = false)
                }
            }
            is AlarmScreenEvent.ShowAlarmRationale -> {
                _state.update {
                    it.copy(isShouldShowAlarmRationale = true)
                }

            }
            AlarmScreenEvent.ShowNotificationRationale -> {
                _state.update {
                    it.copy(isShouldShowNotificationRationale = true)
                }
            }
            AlarmScreenEvent.CloseNotificationRationale -> {
                viewModelScope.launch {
                    rationaleStateUseCases.saveRationaleStatesUseCase(
                        RationaleStateRepository.IS_NOTIFICATION_RATIONALE_SHOWN,
                        true
                    )
                }
                _state.update {
                    it.copy(isShouldShowNotificationRationale = false)
                }
            }
            is Event.CheckAlarmPermissionState -> {
                _state.update {
                    it.copy(isAlarmsEnable = event.isGranted)
                }
            }
            is Event.CheckNotificationPermissionState -> {
                _state.update {
                    it.copy(isNotificationEnable = event.isGranted)
                }
            }
            is AlarmScreenEvent.CheckTimeFormat -> {
                _state.update {
                    it.copy(is24HourFormat = event.is24HourFormat)
                }
            }
            AlarmScreenEvent.ChangeTimePickerInputMode -> {
                _state.update {
                    it.copy(isDialTimePickerInputMode = !it.isDialTimePickerInputMode)
                }
            }
            is AlarmScreenEvent.DisableAlarm -> {
                viewModelScope.launch {
                    alarmUseCases.disableAlarmUseCase(alarmItem = event.alarmItem)
                }
            }
            is AlarmScreenEvent.EnableAlarm -> {
                viewModelScope.launch {
                    alarmUseCases.enableAlarmUseCase(alarmItem = event.alarmItem)
                }
            }
            is AlarmScreenEvent.SetAlarmRepeating -> {
                viewModelScope.launch {
                    alarmUseCases.setAlarmRepeatingUseCase(
                        alarmItem = event.alarmItem,
                        dayIndex = event.dayIndex
                    )
                }
            }
        }
    }
}