package com.vsv.ruleyourtime.notifications.impl

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.text.format.DateFormat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import com.vsv.core.R
import com.vsv.core.data.AlarmScheduler.Companion.ALARM_ITEM_ID
import com.vsv.ruleyourtime.notifications.AppNotification
import com.vsv.ruleyourtime.presentation.AlarmActivity
import com.vsv.ruleyourtime.service.AlarmService
import com.vsv.ruleyourtime.service.AlarmServiceCommands
import java.text.SimpleDateFormat
import java.util.Locale

class AlarmNotification(
    private val context: Context,
): AppNotification {

    private fun convertAlarmTime(millis: Long): String {
        val pattern = if (DateFormat.is24HourFormat(context)) "HH:mm" else "HH:mm a"
        return SimpleDateFormat(pattern, Locale.getDefault()).format(millis)
    }

    override fun createNotificationChanel(): NotificationChannelCompat {
        return NotificationChannelCompat.Builder(
            AlARM_CHANNEL_ID,
            NotificationManager.IMPORTANCE_MAX
        )
            .setName("Active Alarms")
            .setSound(null, null)
            .setVibrationEnabled(true)
            .build()
    }

    override fun createNotification(itemId: Int): Notification {

        val action = NotificationCompat.Action(null, "Stop", setServicePendingIntent(itemId))

        return NotificationCompat.Builder(context,
            AlARM_CHANNEL_ID
        )
            .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
            .setSmallIcon(R.drawable.baseline_alarm_on_24)
            .setContentTitle("Alarm Clock #$itemId")
//            .setContentText("Time ${convertAlarmTime(item.alarmTimeMillis)}")
            .setFullScreenIntent(setActivityPendingIntent(itemId), true)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .addAction(action)
            .build()
    }

    private fun setServicePendingIntent(itemId: Int): PendingIntent? {
        val alarmServiceIntent = Intent(context, AlarmService::class.java).apply {
            action = AlarmServiceCommands.STOP.toString()
            putExtra(ALARM_ITEM_ID, itemId)
        }
        return PendingIntent.getService(
            context,
            0,
            alarmServiceIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun setActivityPendingIntent(itemId: Int): PendingIntent? {
        val activityIntent = Intent(context, AlarmActivity::class.java).apply {
            putExtra(ALARM_ITEM_ID, itemId)
        }
        return PendingIntent.getActivity(
            context,
            0,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    companion object {
        const val AlARM_CHANNEL_ID = "alarm_channel_id"
    }
}
