package com.vsv.ruleyourtime.ui

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vsv.ruleyourtime.AlarmEvent
import com.vsv.ruleyourtime.AlarmsScreenState
import com.vsv.ruleyourtime.alarmclock.AlarmItem
import com.vsv.ruleyourtime.ui.theme.RuleYourTimeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreen(
    state: AlarmsScreenState,
    onEvent: (AlarmEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val timePickerState = rememberTimePickerState(
        is24Hour = DateFormat.is24HourFormat(context)
    )
    Scaffold(
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { onEvent(AlarmEvent.ShowTimePicker) },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add alarm",
                    modifier = Modifier.size(48.dp)
                )
            }
        },
    ) { paddingValues ->

        if (state.isAddingAlarm) {
            TimePickerDialog(
                state = state,
                onEvent = onEvent,
                timePickerState = timePickerState

            )
        }
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = state.alarms) { alarm ->
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
                            text =
                            String.format("%02d", alarm.hours) + ":" + String.format(
                                "%02d",
                                alarm.minutes
                            ),
                            fontSize = 36.sp,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = { onEvent(AlarmEvent.DeleteAlarm(alarm)) }
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
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewAlarmsScreen() {
    RuleYourTimeTheme {
        AlarmsScreen(
            state = AlarmsScreenState(
                alarms = listOf(
                    AlarmItem(
                        id = 8137,
                        isEnabled = false,
                        hours = 12,
                        minutes = 0
                    )
                )
            ),
            onEvent = {},
            navController = NavController(LocalContext.current)
        )

    }
}


//    var showTimePicker by remember {
//        mutableStateOf(false)
//    }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = modifier.fillMaxSize()
//    ) {
//        Button(
//            onClick = { showTimePicker = true },
//            modifier = Modifier
//        ) {
//            Text(text = "Open TimePicker")
//        }
//        Button(
//            onClick = { /*scheduler.disable()*/ },
//            modifier = Modifier
//        ) {
//            Text(text = "next alarm")
//        }
//    }
//    if (showTimePicker) {
//        TimePickerDialog(
//            onDismiss = { showTimePicker = false },
////            onConfirmClick = { Log.d(ContentValues.TAG, "h: ${timePickerState.hour}, m: ${timePickerState.minute}, 24: ${timePickerState.is24hour}")
////                viewModel.getTimeMillis(timePickerState.hour, timePickerState.minute)
////                scheduler.schedule(viewModel.item.value)
////                Log.d(ContentValues.TAG, "RuleYourTimeApp: ${viewModel.item.value.hashCode()}")
////                Log.d(ContentValues.TAG, "RuleYourTimeApp: ${viewModel.item.value}")
////                showTimePicker = false },
//            onConfirmClick = {
//                             viewModel.addAlarm(AlarmItem(
////                                 id = 2,
//                                 isEnabled = true,
//                                 hours = timePickerState.hour,
//                                 minutes = timePickerState.minute
//                             ))
//            },
//            onDismissClick = { showTimePicker = false },
//            timePickerState = timePickerState
//        )
//    }
//}