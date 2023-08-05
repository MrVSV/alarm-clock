package com.vsv.ruleyourtime.presentation.common

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmScreenEvent
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmsScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    state: AlarmsScreenState,
    onEvent: (AlarmScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val timePickerState = rememberTimePickerState(
        initialHour = state.hours,
        initialMinute = state.minutes,
        is24Hour = state.is24HourFormat
    )
    DatePickerDialog(
        modifier = Modifier,
        onDismissRequest = { onEvent(AlarmScreenEvent.CloseTimePicker) },
        confirmButton = {
            TextButton(
                onClick = {
                    onEvent(
                        AlarmScreenEvent.SetAlarmTime(
                            timePickerState.hour,
                            timePickerState.minute
                        )
                    )
                },
            ) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onEvent(AlarmScreenEvent.CloseTimePicker) }
            ) {
                Text(text = "Cancel")
            }
        }
    ) {
        TimePicker(
            state = timePickerState,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )
//        TimeInput(
//            state = timePickerState,
//            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
//        )
    }
}