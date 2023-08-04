package com.vsv.ruleyourtime.presentation.alarms_screen

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import android.text.format.DateFormat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.vsv.ruleyourtime.domain.model.AlarmItem
import com.vsv.ruleyourtime.presentation.common.AlarmRationaleDialog
import com.vsv.ruleyourtime.presentation.common.NotificationRationaleDialog
import com.vsv.ruleyourtime.presentation.common.TimePickerDialog
import com.vsv.ruleyourtime.presentation.ui.theme.RuleYourTimeTheme
import com.vsv.ruleyourtime.utils.findActivity
import kotlinx.coroutines.launch

fun checkAlarmPermissionState(context: Context): Boolean {
    val alarmManager = context.getSystemService(AlarmManager::class.java)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        alarmManager.canScheduleExactAlarms()
    else true
}

fun checkNotificationPermissionState(context: Context): Boolean {
    val notificationManager = NotificationManagerCompat.from(context)
    return notificationManager.areNotificationsEnabled()
}

fun openAlarmPermissionSettings(context: Context){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        context.startActivity(
            Intent(
                ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                Uri.fromParts("package", context.packageName, null)
            )
        )
    }
}

class AlarmPermissionSnackbarVisuals(
    override val message: String,
    override val actionLabel: String,
) : SnackbarVisuals {
    override val duration: SnackbarDuration
        get() = SnackbarDuration.Indefinite
    override val withDismissAction: Boolean
        get() = true

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmsScreen(
    state: AlarmsScreenState,
    onEvent: (AlarmScreenEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    val timePickerState = rememberTimePickerState(
        is24Hour = DateFormat.is24HourFormat(context)
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranded ->
            if (isGranded) onEvent(AlarmScreenEvent.ShowTimePicker)
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
                if (!checkAlarmPermissionState(context)) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            AlarmPermissionSnackbarVisuals(
                                message = "Нужно разрешение будильника",
                                actionLabel = "Настройки"
                            )
                        )
                    }
                }
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
            timePickerState = timePickerState,
        )
    }

    if (state.isShouldShowNotificationRationale) {
        NotificationRationaleDialog(
            onEvent = onEvent,
            text = "Нужно разрешение на уведомления"
        )
    }

    if (state.isShouldShowAlarmRationale) {
        AlarmRationaleDialog(
            permissionRequest = { openAlarmPermissionSettings(context) },
            onEvent = onEvent,
            text = "Перейти к настройкам будильника"
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            ) { data ->
                Snackbar(
                    dismissAction = {
                        IconButton(onClick = { snackbarHostState.currentSnackbarData?.dismiss() }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    },
                    action = {
                        TextButton(
                            onClick = { openAlarmPermissionSettings(context) }
                        ) {
                            Text(text = data.visuals.actionLabel ?: "")
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = data.visuals.message)
                }
            }

        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    if (!checkAlarmPermissionState(context)) {
                        onEvent(AlarmScreenEvent.ShowAlarmRationale)
                    } else if (!checkNotificationPermissionState(context)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    } else {
                        onEvent(AlarmScreenEvent.ShowTimePicker)
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

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = state.alarms) { alarm ->
                AlarmsScreenItem(alarm = alarm, state = state, onEvent = onEvent)
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
