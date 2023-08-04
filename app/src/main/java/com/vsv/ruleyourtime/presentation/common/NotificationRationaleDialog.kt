package com.vsv.ruleyourtime.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmScreenEvent

@Composable
fun NotificationRationaleDialog(
    onEvent: (AlarmScreenEvent) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = { onEvent(AlarmScreenEvent.CloseRationaleDialog) },
        confirmButton = {
            TextButton(
                onClick = {
                    onEvent(AlarmScreenEvent.CloseRationaleDialog)
                }
            ) {
                Text(text = "OK")
            }
        },
        text = { Text(text) },
        modifier = modifier
    )
}