package com.vsv.ruleyourtime.alarmclock

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
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

    override fun disable(/*item: AlarmItem*/) {
        Log.d(TAG, "disable: ${alarmManager.nextAlarmClock?.triggerTime}")
    }

    override fun cancel(item: AlarmItem) {

    }

    private fun setAlarmInfo(item: AlarmItem): AlarmClockInfo {
        val alarmInfoIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val alarmInfoPendingIntent = PendingIntent.getActivity(
            context, 0, alarmInfoIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        return AlarmClockInfo(
            item.alarmTimeMillis,
            alarmInfoPendingIntent
        )
    }

    private fun setAlarmPendingIntent(item: AlarmItem): PendingIntent {
        val alarmIntent = Intent(context, AlarmReceiver::class.java).also {
            it.putExtra("alarm_item", item)
        }
        return PendingIntent.getBroadcast(
            context, item.id, alarmIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

}