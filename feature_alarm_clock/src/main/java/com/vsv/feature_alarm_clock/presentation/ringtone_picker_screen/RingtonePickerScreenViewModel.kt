package com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsv.core.domain.RingtonePicker
import com.vsv.feature_alarm_clock.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RingtonePickerScreenViewModel(
    private val repository: Repository,
    private val ringtonePicker: RingtonePicker,
) : ViewModel() {

    private val _state = MutableStateFlow(
        RingtonePickerScreenState(
            alarmItem = null,
            ringtonesList = ringtonePicker.getRingtonesList(),
            defaultRingtone = ringtonePicker.getRingtone(),
            lastPickedRingtoneUri = null
        )
    )

    val state = _state.asStateFlow()

    fun onEvent(event: RingtonePickerScreenEvent) {
        when (event) {
            is RingtonePickerScreenEvent.SetRingtone -> {
                viewModelScope.launch {
                    ringtonePicker.setRingtone(event.ringtone)
                    val alarm = repository.getAlarmById(event.alarmItemId)
                    repository.updateAlarm(
                        alarm.copy(
                            ringtoneTitle = event.ringtone.title,
                            ringtoneUri = event.ringtone.uri
                        )
                    )
                }
            }
        }

    }
}