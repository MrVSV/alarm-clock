package com.vsv.feature_alarm_clock.data.alarm_clock.receivers

import android.app.AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vsv.feature_alarm_clock.domain.repository.Repository
import org.koin.java.KoinJavaComponent

class AlarmPermissionStateChangingReceiver : BroadcastReceiver() {

    private val repository: Repository by KoinJavaComponent.inject(Repository::class.java)

    override fun onReceive(context: Context?, intent: Intent?) {
//        val alarmManager = context?.getSystemService(AlarmManager::class.java)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            if (alarmManager?.canScheduleExactAlarms() == false) {
//                Intent().apply {
//                    action = ACTION_REQUEST_SCHEDULE_EXACT_ALARM
//                }.also {
//                    context.applicationContext.startActivity(it)
//                }
//            }
//        }

        if (intent?.action == ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED) {
            println("onReceive: ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED")
            repository.resetAllAlarms()
//            context?.applicationContext?.unregisterReceiver(this)
        }

    }


}