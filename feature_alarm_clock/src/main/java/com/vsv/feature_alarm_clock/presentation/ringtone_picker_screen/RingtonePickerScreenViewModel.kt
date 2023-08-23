package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.feature_alarm_clock.domain.use_case.AlarmUseCases
import com.vsv.feature_alarm_clock.domain.use_case.RingtoneUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RingtonePickerScreenViewModel(
    private val alarmUseCases: AlarmUseCases,
    private val ringtoneUseCases: RingtoneUseCases,
) : ViewModel() {

    private val _ringtoneList = MutableStateFlow<List<MyRingtone>>(emptyList())
    val ringtonesList = _ringtoneList.asStateFlow()

    private val _state = MutableStateFlow(RingtonePickerScreenState())
    val state = _state.asStateFlow()

    init {
        getRingtonesList()
        getDefaultRingtone()
    }

    private fun getRingtonesList() {
        viewModelScope.launch {
            _ringtoneList.emit(ringtoneUseCases.getRingtonesListUseCase())
            Log.d(TAG, "getRingtonesList: ${ringtonesList.value.size}")
        }
    }

    private fun getDefaultRingtone() {
        viewModelScope.launch {
            _state.update {
                it.copy(defaultRingtone = ringtoneUseCases.getRingtoneUseCase())
            }
        }
    }

    fun onEvent(event: RingtonePickerScreenEvent) {
        when (event) {
            is RingtonePickerScreenEvent.SetRingtone -> {
                viewModelScope.launch {
                    ringtoneUseCases.setRingtoneUseCase(event.alarmItemId, event.ringtone)
                }
            }
            is RingtonePickerScreenEvent.AddUserRingtone -> {
                viewModelScope.launch {
                    ringtoneUseCases.addUserRingtoneUseCase(event.myRingtone)
                    getRingtonesList()
                }
            }
        }
    }
}


