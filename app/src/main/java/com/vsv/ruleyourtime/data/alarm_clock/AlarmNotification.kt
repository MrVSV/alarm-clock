package com.vsv.ruleyourtime.data.alarm_clock

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.text.format.DateFormat
import androidx.core.app.NotificationCompat
import com.vsv.ruleyourtime.presentation.AlarmActivity
import com.vsv.ruleyourtime.R
import java.text.SimpleDateFormat
import java.util.Locale

class AlarmNotification(
    private val context: Context,
): AppNotification {

    private fun convertAlarmTime(millis: Long): String {
        val pattern = if (DateFormat.is24HourFormat(context)) "HH:mm" else "HH:mm a"
        return SimpleDateFormat(pattern, Locale.getDefault()).format(millis)
    }

    override fun getNotification(itemId: Int): Notification {
        val activityIntent = Intent(context, AlarmActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(
                context,
                0,
                activityIntent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        val alarmServiceIntent = Intent(context, AlarmService::class.java).apply {
            action = AlarmServiceCommands.STOP.toString()
        }
        val alarmSoundPendingIntent =
            PendingIntent.getService(
                context,
                0,
                alarmServiceIntent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        val action = NotificationCompat.Action(null, "Stop", alarmSoundPendingIntent)

        return NotificationCompat.Builder(context, AlARM_CHANNEL_ID)
            .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Alarm Clock #$itemId")
//            .setContentText("Time ${convertAlarmTime(item.alarmTimeMillis)}")
            .setFullScreenIntent(pendingIntent, true)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .addAction(action)
            .build()
    }

    companion object {
        const val AlARM_CHANNEL_ID = "alarm_channel_id"
    }
}
