package com.vsv.core.data

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vsv.core.domain.model.Item
import com.vsv.core.domain.scheduler.ScheduleResult
import com.vsv.core.domain.scheduler.Scheduler
import com.vsv.core.utils.MyCalendar

class AlarmScheduler(
    private val context: Context,
    private val calendar: MyCalendar,
) : Scheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun  schedule(item: Item): ScheduleResult {
        return try {
            alarmManager.setAlarmClock(
                setAlarmInfo(item),
                setAlarmPendingIntent(item)
            )
            ScheduleResult.Success
        } catch (e: Exception) {
            ScheduleResult.Error(e)
        }
    }

    override fun disable(item: Item) {
        Log.d(TAG, "disable: ${alarmManager.nextAlarmClock.triggerTime}")
    }

    override fun cancel(item: Item) {
        alarmManager.cancel(setAlarmPendingIntent(item))
    }

    private fun  setAlarmInfo(item: Item): AlarmClockInfo {
        val alarmInfoIntent = Intent("com.vsv.app.mainactivity").apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val alarmInfoPendingIntent = PendingIntent.getActivity(
            context,
            item.id,
            alarmInfoIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d(TAG, "setAlarmInfo: ${item.hours}:${item.minutes}")
        return AlarmClockInfo(
            calendar.convertToMillis(item.hours, item.minutes),
            alarmInfoPendingIntent
        )
    }

    private fun setAlarmPendingIntent(item: Item): PendingIntent {
        val alarmIntent = Intent().apply {
            setClassName(context, "com.vsv.ruleyourtime.receivers.AlarmReceiver")
            putExtra(ALARM_ITEM_ID, item.id)
        }
        return PendingIntent.getBroadcast(
            context,
            item.id,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    companion object{
        const val ALARM_ITEM_ID = "alarm_item_id"
    }
}