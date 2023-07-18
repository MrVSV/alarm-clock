package com.vsv.ruleyourtime

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vsv.ruleyourtime.alarmclock.AlarmScheduler
import com.vsv.ruleyourtime.ui.theme.RuleYourTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuleYourTimeTheme {
                RuleYourTimeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RuleYourTimeApp(
    modifier: Modifier = Modifier,
) {
    var showTimePicker by remember {
        mutableStateOf(false)
    }
    val state = rememberTimePickerState(is24Hour = false)
    val context = LocalContext.current
    val scheduler =  AlarmScheduler(context)
    val viewModel = viewModel<AlarmsScreenViewModel>()

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
    }
    if (showTimePicker) {
        DatePickerDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.getTimeMillis(state.hour, state.minute)
                        scheduler.schedule(viewModel.item.value)
                        Log.d(TAG, "RuleYourTimeApp: ${viewModel.item.value}")
                        showTimePicker = false
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showTimePicker = false }
                ) {
                    Text(text = "Cancel")
                }
            },
        ) {
            Text(
                text = "Select time",
                modifier = Modifier.padding(8.dp))
            TimePicker(
                state = state,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RuleYourTimeTheme {
        RuleYourTimeApp()
    }
}
