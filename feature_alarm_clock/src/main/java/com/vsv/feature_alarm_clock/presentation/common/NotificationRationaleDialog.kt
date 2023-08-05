package com.vsv.feature_alarm_clock.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmScreenEvent

@Composable
fun NotificationRationaleDialog(
    onEvent: (AlarmScreenEvent) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = { onEvent(AlarmScreenEvent.CloseNotificationRationale) },
        confirmButton = {
            TextButton(
                onClick = {
                    onEvent(AlarmScreenEvent.CloseNotificationRationale)
                }
            ) {
                Text(text = "OK")
            }
        },
        text = { Text(text) },
        modifier = modifier
    )
}