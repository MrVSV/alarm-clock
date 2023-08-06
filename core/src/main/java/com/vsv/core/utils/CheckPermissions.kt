package com.vsv.core.utils

import android.app.AlarmManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat

fun checkAlarmPermissionState(context: Context, onEvent: (Event) -> Unit) {
    val alarmManager = context.getSystemService(AlarmManager::class.java)
    val isGranded =  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        alarmManager.canScheduleExactAlarms()
    else true
    onEvent(Event.CheckAlarmPermissionState(isGranded))
}

fun checkNotificationPermissionState(context: Context, onEvent: (Event) -> Unit) {
    val notificationManager = NotificationManagerCompat.from(context)
    onEvent(Event.CheckNotificationPermissionState(notificationManager.areNotificationsEnabled()))
}