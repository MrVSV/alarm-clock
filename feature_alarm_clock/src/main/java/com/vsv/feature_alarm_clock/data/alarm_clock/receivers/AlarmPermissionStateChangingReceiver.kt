package com.vsv.feature_alarm_clock.data.alarm_clock.receivers

import android.app.AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vsv.feature_alarm_clock.domain.repository.Repository
import org.koin.java.KoinJavaComponent.inject


class AlarmPermissionStateChangingReceiver : BroadcastReceiver() {

    private val repository: Repository by inject(Repository::class.java)

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED) {
            println("onReceive: ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED")
            repository.resetAllAlarms()
        }
    }
}