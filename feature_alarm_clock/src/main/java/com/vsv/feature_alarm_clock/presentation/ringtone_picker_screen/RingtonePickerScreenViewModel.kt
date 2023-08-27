package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.use_case.AlarmUseCases
import com.vsv.feature_alarm_clock.domain.use_case.RingtoneUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RingtonePickerScreenViewModel(
    private val alarmUseCases: AlarmUseCases,
    private val ringtoneUseCases: RingtoneUseCases,
) : ViewModel() {

    private val _alarmItem = MutableStateFlow(AlarmItem())

    private val _userRingtonesList = ringtoneUseCases.getUserRingtonesListUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
    private val _deviceRingtoneList = MutableStateFlow<List<MyRingtone>>(emptyList())

    private val _state = MutableStateFlow(RingtonePickerScreenState())
    val state = combine(
        _state,
        _alarmItem,
        _userRingtonesList,
        _deviceRingtoneList
    ) { state, alarmItem, user, device ->
        state.copy(
            alarmItem = alarmItem,
            ringtoneList = user + device
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RingtonePickerScreenState()
    )

    private fun getRingtonesList() {
        viewModelScope.launch {
            _deviceRingtoneList.update { ringtoneUseCases.getDeviceRingtonesListUseCase() }
        }
    }

    fun onEvent(event: RingtonePickerScreenEvent) {
        when (event) {
            is RingtonePickerScreenEvent.SetRingtone -> {
                viewModelScope.launch {
                    ringtoneUseCases.setRingtoneUseCase(event.alarmItem, event.ringtone)
                }
            }
            is RingtonePickerScreenEvent.AddUserRingtone -> {
                viewModelScope.launch {
                    ringtoneUseCases.addUserRingtoneUseCase(event.uri)
                }
            }
            is RingtonePickerScreenEvent.DeleteUserRingtone -> {
                viewModelScope.launch {
                    ringtoneUseCases.deleteUserRingtoneUseCase(event.ringtone)
                    _alarmItem.update { alarmUseCases.getAlarmByIdUseCase(event.alarmItemId) }
                    _state.update {
                        it.copy(
                            userRingtoneHasBeenDeleted = true
                        )
                    }
                }
            }
            is RingtonePickerScreenEvent.GetAlarmById -> {
                viewModelScope.launch {
                    getRingtonesList()
                    _alarmItem.update { alarmUseCases.getAlarmByIdUseCase(event.alarmItemId) }
                    _state.update {
                        it.copy( hasItemBeenReceived = true)
                    }
                }
            }
        }
    }
}


