package com.vsv.ruleyourtime.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmScreenEvent

@Composable
fun AlarmRationaleDialog(
    permissionRequest: () -> Unit,
    onEvent: (AlarmScreenEvent) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = { onEvent(AlarmScreenEvent.CloseAlarmRationale) },
        confirmButton = {
            TextButton(
                onClick = {
                    permissionRequest()
                    onEvent(AlarmScreenEvent.CloseAlarmRationale)
                }
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onEvent(AlarmScreenEvent.CloseAlarmRationale)
                }
            )
            { Text(text = "Cancel") }
        },
        text = { Text(text) },
        modifier = modifier
    )
}