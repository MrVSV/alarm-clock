package com.vsv.ruleyourtime.ui

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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    timePickerState: TimePickerState
) {
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirmClick,
            ) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissClick
            ) {
                Text(text = "Cancel")
            }
        }
    ) {
        Text(
            text = "Select time",
            modifier = Modifier.padding(8.dp))
        TimePicker(
            state = timePickerState,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
    }

}