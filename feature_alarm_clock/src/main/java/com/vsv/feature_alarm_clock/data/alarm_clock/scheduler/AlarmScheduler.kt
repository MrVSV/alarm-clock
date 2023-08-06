package com.vsv.feature_alarm_clock.data.alarm_clock.scheduler

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vsv.core.utils.MyCalendar
import com.vsv.feature_alarm_clock.data.alarm_clock.receivers.AlarmReceiver
import com.vsv.feature_alarm_clock.domain.alarm_clock.Scheduler
import com.vsv.local_data_base.data_base.AlarmItemEntity

class AlarmScheduler(
    private val context: Context,
    private val calendar: MyCalendar,
) : Scheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @SuppressLint("ScheduleExactAlarm")
    override fun schedule(entity: AlarmItemEntity) {
        Log.d(TAG, "schedule: $entity")
        alarmManager.setAlarmClock(
            setAlarmInfo(entity),
            setAlarmPendingIntent(entity)
        )
    }

    override fun disable(entity: AlarmItemEntity) {
        Log.d(TAG, "disable: ${alarmManager.nextAlarmClock.triggerTime}")
    }

    override fun cancel(entity: AlarmItemEntity) {
        alarmManager.cancel(setAlarmPendingIntent(entity))
    }

    private fun setAlarmInfo(entity: AlarmItemEntity): AlarmClockInfo {
        val alarmInfoIntent = Intent("com.vsv.app.mainactivity").apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val alarmInfoPendingIntent = PendingIntent.getActivity(
            context,
            entity.id,
            alarmInfoIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
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
            context,
            entity.id,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}