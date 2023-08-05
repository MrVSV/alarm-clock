package com.vsv.ruleyourtime.presentation.alarms_screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.ruleyourtime.domain.model.AlarmItem
import com.vsv.ruleyourtime.domain.preferences.UserPreferences
import com.vsv.ruleyourtime.domain.repository.Repository
import com.vsv.ruleyourtime.domain.repository.UserPreferencesRepository
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
                hours = LocalTime.now().hour + 1,
                minutes = LocalTime.now().minute,
                isAlarmRationaleShown = prefs.isAlarmRationaleShown,
                isNotificationRationaleShown = prefs.isNotificationRationaleShown
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = AlarmsScreenState()
        )

    fun onEvent(event: AlarmScreenEvent) {
        when (event) {
            AlarmScreenEvent.ShowTimePicker -> {
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
                    if (repository.addAlarm(alarmItem = alarmItem)) {
                        _state.update {
                            it.copy(isAddingAlarm = false)
                        }
                    } else {
                        Log.d(TAG, "onEvent: error")
                        _state.update {
                            it.copy(isAddingAlarm = false, isError = true)
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
            is AlarmScreenEvent.CheckAlarmPermissionState -> {
                _state.update {
                    it.copy(isAlarmsEnable = event.isGranted)
                }
            }
            is AlarmScreenEvent.CheckNotificationPermissionState -> {
                _state.update {
                    it.copy(isNotificationEnable = event.isGranted)
                }
            }
            is AlarmScreenEvent.CheckTimeFormat -> {
                _state.update {
                    it.copy(is24HourFormat = event.is24HourFormat)
                }
            }
        }
    }
}