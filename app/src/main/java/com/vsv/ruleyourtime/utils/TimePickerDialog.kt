package com.vsv.ruleyourtime.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmScreenEvent
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmsScreenState
import com.vsv.ruleyourtime.presentation.ui.theme.RuleYourTimeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    state: AlarmsScreenState,
    onEvent: (AlarmScreenEvent) -> Unit,
    timePickerState: TimePickerState,
    modifier: Modifier = Modifier,
) {
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
        Text(
            text = "Select time",
            modifier = Modifier.padding(8.dp)
        )
        TimePicker(
            state = timePickerState,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TimePickerDialogPreview() {
    RuleYourTimeTheme() {
        TimePickerDialog(
            state = AlarmsScreenState(),
            onEvent = {},
            timePickerState = TimePickerState(
                12,
                0,
                true
            )
        )
    }
}