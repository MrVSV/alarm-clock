package com.vsv.feature_alarm_clock.presentation.alarms_screen

import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RingtonePickerDialog(
    state: AlarmsScreenState,
    onEvent: (AlarmScreenEvent) -> Unit,
    ringtones: MutableMap<Uri, String>,
    modifier: Modifier = Modifier,
) {
    val map by remember {
        derivedStateOf { ringtones.map { it.key to it.value } }
    }

    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Text(text = "Confirm")
        },
        title = {
            Text(text = "select")
        },
        dismissButton = {
            Text(text = "cancel")
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .selectableGroup()
            ) {
                items(items = map) {
//                    Log.d(TAG, "RingtonePickerDialog: ${map.indexOf(it)}")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = false,
                            onClick = {},
                            )
                        Text(text = "${it.second} ${map.indexOf(it)}")
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}
