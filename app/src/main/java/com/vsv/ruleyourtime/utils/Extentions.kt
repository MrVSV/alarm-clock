package com.vsv.ruleyourtime.utils

import android.app.Activity
import android.app.AlarmManager
import android.app.KeyguardManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.view.WindowManager
import androidx.core.app.NotificationManagerCompat
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmScreenEvent

fun Context.openAlarmPermissionSettings() {
    if (SDK_INT >= Build.VERSION_CODES.S) {
        this.startActivity(
            Intent(
                Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                Uri.fromParts("package", this.packageName, null)
            )
        )
    }
}

fun Context.openNotificationPermissionSettings() {
    if (SDK_INT >= Build.VERSION_CODES.S) {
        this.startActivity(
            Intent().apply {
                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra(Settings.EXTRA_APP_PACKAGE, "com.vsv.ruleyourtime")
            }
        )
    }
}

fun AlarmManager.checkAlarmPermissionState(onEvent: (AlarmScreenEvent) -> Unit) {
    val isGranded =  if (SDK_INT >= Build.VERSION_CODES.S)
        this.canScheduleExactAlarms()
    else true
    onEvent(AlarmScreenEvent.CheckAlarmPermissionState(isGranded))
}

fun NotificationManagerCompat.checkNotificationPermissionState(onEvent: (AlarmScreenEvent) -> Unit) {
   onEvent(AlarmScreenEvent.CheckNotificationPermissionState(this.areNotificationsEnabled()))
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}

fun Activity.turnScreenOnAndKeyguardOff() {
    if (SDK_INT >= Build.VERSION_CODES.O_MR1) {
        setShowWhenLocked(true)
        setTurnScreenOn(true)
    } else {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )
    }
    with(getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager) {
        requestDismissKeyguard(this@turnScreenOnAndKeyguardOff, null)
    }
}

fun Activity.turnScreenOffAndKeyguardOn() {
    if (SDK_INT >= Build.VERSION_CODES.O_MR1) {
        setShowWhenLocked(false)
        setTurnScreenOn(false)
    } else {
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )
    }
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}