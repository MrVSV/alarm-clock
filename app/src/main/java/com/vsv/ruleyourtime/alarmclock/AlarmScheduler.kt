package com.vsv.ruleyourtime.alarmclock

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.vsv.ruleyourtime.MainActivity

class AlarmScheduler(
    private val context: Context,
) : Scheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(item: AlarmItem) {
        alarmManager.setAlarmClock(
            setAlarmInfo(item),
            setAlarmPendingIntent(item)
        )
    }

    override fun disable(item: AlarmItem) {

    }

    override fun cancel(item: AlarmItem) {

    }

    private fun setAlarmInfo(item: AlarmItem): AlarmClockInfo {
        val alarmInfoIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val alarmInfoPendingIntent = PendingIntent.getActivity(
            context, 0, alarmInfoIntent, PendingIntent.FLAG_IMMUTABLE
        )
        return AlarmClockInfo(
            item.alarmTime,
            alarmInfoPendingIntent
        )
    }

    private fun setAlarmPendingIntent(item: AlarmItem): PendingIntent {
        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("alarm_item_id", item.id.toString())
            putExtra("alarm_item_alarm_time", item.alarmTime)
        }
        return PendingIntent.getBroadcast(
            context, item.id, alarmIntent, PendingIntent.FLAG_IMMUTABLE
        )
    }

}