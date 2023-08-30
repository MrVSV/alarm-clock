package com.vsv.feature_alarm_clock.presentation.alarms_screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vsv.core.domain.navigation.Destination
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreenItem(
    alarm: AlarmItem,
    state: AlarmsScreenState,
    onEvent: (AlarmScreenEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val pattern = if (state.is24HourFormat) "HH:mm" else "hh:mm a"
    val timeFormat = DateTimeFormatter.ofPattern(pattern)
    val time = LocalTime.parse(
        String.format("%02d", alarm.hours) + ":" + String.format("%02d", alarm.minutes),
        DateTimeFormatter.ofPattern("HH:mm")
    )
    val days = DayOfWeek.values()
    Card(
        onClick = {
            val today = LocalDate.now().dayOfWeek.value
            var i = today
            repeat(7) {
                if (!alarm.alarmDays[i - 1]) {
                    i = if (i == 7) 1 else i + 1
                } else return@repeat
            }
            val daysToAdd = if (i < today) i - today + 7 else i - today
            Log.d(TAG, "AlarmsScreenItem: $daysToAdd")
            Log.d(TAG, "AlarmsScreenItem: ${alarm.isRepeating()}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp)
            .testTag("alarm_item")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = time.format(timeFormat),
                fontSize = 36.sp,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onEvent(AlarmScreenEvent.ShowTimePicker(alarmItem = alarm)) }
            )
            Switch(
                checked = alarm.isEnabled,
                enabled = state.isAlarmsEnable,
                onCheckedChange = {
                    if (alarm.isEnabled)
                        onEvent(AlarmScreenEvent.DisableAlarm(alarmItem = alarm))
                    else
                        onEvent(AlarmScreenEvent.EnableAlarm(alarmItem = alarm))
                },
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
        ) {
            days.forEachIndexed { index, day ->
                OutlinedIconToggleButton(
                    checked = alarm.alarmDays[index],
                    onCheckedChange = {
                        onEvent(
                            AlarmScreenEvent.SetAlarmRepeating(
                                alarmItem = alarm,
                                dayIndex = index
                            )
                        )
                    }
                ) {
                    Text(
                        text = day.getDisplayName(
                            TextStyle.NARROW,
                            Locale.getDefault()
                        )
                    )
                }
            }
        }
        TextButton(
            onClick = {
                navController.navigate(Destination.RingtonePickerScreen.route + "/${alarm.id}")
            },
            Modifier.padding(start = 16.dp)
        ) {
            Text(text = alarm.ringtone.title)
        }
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "delete alarm",
            modifier = Modifier
                .padding(16.dp)
                .clickable(
                    enabled = true,
                    onClick = { onEvent(AlarmScreenEvent.DeleteAlarm(alarmItem = alarm)) }
                )
        )
    }
}