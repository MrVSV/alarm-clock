package com.vsv.ruleyourtime.ui

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.vsv.ruleyourtime.AlarmsScreenViewModel
import com.vsv.ruleyourtime.alarmclock.AlarmItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreen(
    navController: NavController,
    viewModel: AlarmsScreenViewModel,
    modifier: Modifier = Modifier,
) {
    var showTimePicker by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val timePickerState = rememberTimePickerState(is24Hour = DateFormat.is24HourFormat(context))
//    val scheduler =  AlarmScheduler(context)
//    val viewModel = viewModel<AlarmsScreenViewModel>()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = { showTimePicker = true },
            modifier = Modifier
        ) {
            Text(text = "Open TimePicker")
        }
        Button(
            onClick = { /*scheduler.disable()*/ },
            modifier = Modifier
        ) {
            Text(text = "next alarm")
        }
    }
    if (showTimePicker) {
        TimePickerDialog(
            onDismiss = { showTimePicker = false },
//            onConfirmClick = { Log.d(ContentValues.TAG, "h: ${timePickerState.hour}, m: ${timePickerState.minute}, 24: ${timePickerState.is24hour}")
//                viewModel.getTimeMillis(timePickerState.hour, timePickerState.minute)
//                scheduler.schedule(viewModel.item.value)
//                Log.d(ContentValues.TAG, "RuleYourTimeApp: ${viewModel.item.value.hashCode()}")
//                Log.d(ContentValues.TAG, "RuleYourTimeApp: ${viewModel.item.value}")
//                showTimePicker = false },
            onConfirmClick = {
                             viewModel.addAlarm(AlarmItem(
//                                 id = 2,
                                 isEnabled = true,
                                 hours = timePickerState.hour,
                                 minutes = timePickerState.minute
                             ))
            },
            onDismissClick = { showTimePicker = false },
            timePickerState = timePickerState
        )
    }
}