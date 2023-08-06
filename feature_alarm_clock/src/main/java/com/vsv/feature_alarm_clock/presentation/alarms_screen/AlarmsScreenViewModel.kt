package com.vsv.feature_alarm_clock.presentation.alarms_screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.core.domain.ScheduleResult
import com.vsv.core.utils.Event
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.preferences.UserPreferences
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime

class AlarmsScreenViewModel(
    private val repository: Repository,
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {

    private val _alarms = repository.getAlarmList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    private val _userPreferences = userPreferencesRepository.getFromPreferences().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UserPreferences()
    )

    private val _state = MutableStateFlow(AlarmsScreenState())
    val state =
        combine(_state, _alarms, _userPreferences) { state, alarms, prefs ->
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
                val alarmItem = AlarmItem(
                    id = event.id,
                    hours = event.hours,
                    minutes = event.minutes,
                    isEnabled = true
                )
                viewModelScope.launch {
                    when (repository.addAlarm(alarmItem = alarmItem)) {
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
                    repository.deleteAlarm(alarmItem = event.alarmItem)
                }
            }
            AlarmScreenEvent.OnSnackbarClose -> {
                _state.update {
                    it.copy(isError = false)
                }
            }
            AlarmScreenEvent.CloseAlarmRationale -> {
                viewModelScope.launch {
                    userPreferencesRepository.saveToPreferences(
                        UserPreferencesRepository.IS_ALARM_RATIONALE_SHOWN,
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
                    userPreferencesRepository.saveToPreferences(
                        UserPreferencesRepository.IS_NOTIFICATION_RATIONALE_SHOWN,
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
                val alarmItem = AlarmItem(
                    id = event.alarmItem.id,
                    hours = event.alarmItem.hours,
                    minutes = event.alarmItem.minutes,
                    isEnabled = false,
                )
                viewModelScope.launch {
                    repository.disableAlarm(alarmItem = alarmItem)
                }
            }
            is AlarmScreenEvent.EnableAlarm -> {
                val alarmItem = AlarmItem(
                    id = event.alarmItem.id,
                    hours = event.alarmItem.hours,
                    minutes = event.alarmItem.minutes,
                    isEnabled = true,
                )
                viewModelScope.launch {
                    repository.addAlarm(alarmItem = alarmItem)
                }
            }
        }
    }
}