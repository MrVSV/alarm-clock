package com.vsv.feature_alarm_clock.presentation.alarms_screen

import android.Manifest
import android.content.ContentValues.TAG
import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.vsv.core.extensions.findActivity
import com.vsv.core.extensions.openAlarmPermissionSettings
import com.vsv.core.extensions.openNotificationPermissionSettings
import com.vsv.core.utils.Event
import com.vsv.core.utils.checkAlarmPermissionState
import com.vsv.core.utils.checkNotificationPermissionState
import com.vsv.feature_alarm_clock.presentation.common.AlarmRationaleDialog
import com.vsv.feature_alarm_clock.presentation.common.NotificationRationaleDialog
import com.vsv.feature_alarm_clock.presentation.common.TimePickerDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlarmsScreen(
    state: AlarmsScreenState,
    onEvent: (Event) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranded ->
            if (isGranded) onEvent(AlarmScreenEvent.ShowTimePicker(state.selectedAlarm))
            else {
                if (context.findActivity()
                        .shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)
                ) {
                    onEvent(AlarmScreenEvent.ShowNotificationRationale)
                }
            }
        }
    )

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                checkAlarmPermissionState(context, onEvent)
                checkNotificationPermissionState(context, onEvent)
                onEvent(AlarmScreenEvent.CheckTimeFormat(DateFormat.is24HourFormat(context)))
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    if (state.isAddingAlarm) {
        TimePickerDialog(
            state = state,
            onEvent = onEvent,
        )
    }

    if (state.isShouldShowNotificationRationale) {
        NotificationRationaleDialog(
            onEvent = onEvent,
            text = "Нужно разрешение на уведомления"
        )
    }

    if (state.isShouldShowAlarmRationale && !state.isAlarmRationaleShown) {
        AlarmRationaleDialog(
            permissionRequest = { context.openAlarmPermissionSettings() },
            onEvent = onEvent,
            text = "Перейти к настройкам будильника"
        )
    }

    Scaffold(
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    Log.d(TAG, "AlarmsScreen: $state")
                    if (!state.isAlarmsEnable) {
                        onEvent(AlarmScreenEvent.ShowAlarmRationale)
                    } else if (!state.isNotificationEnable) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    } else {
                        onEvent(AlarmScreenEvent.ShowTimePicker(state.selectedAlarm))
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add alarm",
                    modifier = Modifier.size(48.dp)
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                if (!state.isAlarmsEnable && state.isAlarmRationaleShown)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "нет разрешения будильника")
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { context.openAlarmPermissionSettings() },
                        ) {
                            Text(
                                text = "настройки",
                                fontSize = 16.sp
                            )
                        }
                    }

                if (!state.isNotificationEnable && state.isNotificationRationaleShown)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "нет разрешения уведомлений")
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { context.openNotificationPermissionSettings() },
                        ) {
                            Text(
                                text = "настройки",
                                fontSize = 16.sp
                            )
                        }
                    }
            }
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = state.alarms) { alarm ->
                    AlarmsScreenItem(
                        alarm = alarm,
                        state = state,
                        onEvent = onEvent,
                    )
                }
            }
        }
    }
}