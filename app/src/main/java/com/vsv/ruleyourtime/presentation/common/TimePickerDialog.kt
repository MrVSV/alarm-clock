package com.vsv.ruleyourtime.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vsv.ruleyourtime.R
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
        confirmButton = {}
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            if (state.isDialTimePickerInputMode)
                TimePicker(state = timePickerState)
            else
                TimeInput(state = timePickerState)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            IconButton(
                onClick = { onEvent(AlarmScreenEvent.ChangeTimePickerInputMode) }
            ) {
                Icon(
                    painter = painterResource(
                        id =
                        if (state.isDialTimePickerInputMode)
                            R.drawable.outline_keyboard_24
                        else R.drawable.outline_watch_later_24
                    ),
                    contentDescription = "change time picker input type",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { onEvent(AlarmScreenEvent.CloseTimePicker) }
            ) {
                Text(text = "Cancel")
            }
            TextButton(
                onClick = {
                    onEvent(
                        AlarmScreenEvent.SetAlarmTime(
                            hours = timePickerState.hour,
                            minutes = timePickerState.minute,
                        )
                    )
                },
            ) {
                Text(text = "Confirm")
            }
        }
    }
}