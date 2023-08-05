package com.vsv.ruleyourtime.presentation.alarms_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vsv.ruleyourtime.domain.model.AlarmItem
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreenItem(
    alarm: AlarmItem,
    state: AlarmsScreenState,
    onEvent: (AlarmScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pattern = if (state.is24HourFormat) "HH:mm" else "hh:mm a"
    val timeFormat = DateTimeFormatter.ofPattern(pattern)
    val time = LocalTime.parse(
        String.format("%02d", alarm.hours) + ":" + String.format("%02d", alarm.minutes),
        DateTimeFormatter.ofPattern("HH:mm")
    )
    Card(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = time.format(timeFormat),
                fontSize = 36.sp,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { onEvent(AlarmScreenEvent.DeleteAlarm(alarm)) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete alarm",
                    modifier = Modifier
                        .size(36.dp)
                )
            }
        }
    }
}