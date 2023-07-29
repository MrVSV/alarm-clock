package com.vsv.ruleyourtime.data.alarmclock

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.vsv.ruleyourtime.presentation.MainActivity
import com.vsv.ruleyourtime.data.localdb.AlarmItemEntity

class AlarmScheduler(
    private val context: Context,
    private val calendar: MyCalendar
) : Scheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(entity: AlarmItemEntity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d(TAG, "schedule: ${alarmManager.canScheduleExactAlarms()}")
        }
        alarmManager.setAlarmClock(
            setAlarmInfo(entity),
            setAlarmPendingIntent(entity)
        )
    }

    override fun disable(entity: AlarmItemEntity) {
        Log.d(TAG, "disable: ${alarmManager.nextAlarmClock?.triggerTime}")
    }

    override fun cancel(entity: AlarmItemEntity) {
        alarmManager.cancel(setAlarmPendingIntent(entity))
    }

    private fun setAlarmInfo(entity: AlarmItemEntity): AlarmClockInfo {
        val alarmInfoIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val alarmInfoPendingIntent = PendingIntent.getActivity(
            context, entity.id, alarmInfoIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d(TAG, "setAlarmInfo: ${entity.hours}:${entity.minutes}")
        return AlarmClockInfo(
            calendar.convertToMillis(entity.hours, entity.minutes),
            alarmInfoPendingIntent
        )
    }

    private fun setAlarmPendingIntent(entity: AlarmItemEntity): PendingIntent {
        val alarmIntent = Intent(context, AlarmReceiver::class.java).also {
            it.putExtra("alarm_item", entity.id)
        }
        return PendingIntent.getBroadcast(
            context, entity.id, alarmIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

}