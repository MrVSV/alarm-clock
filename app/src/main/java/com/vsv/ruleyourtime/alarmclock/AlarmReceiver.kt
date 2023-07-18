package com.vsv.ruleyourtime.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val service = NotificationService(context)
//        val itemId = intent?.getIntExtra("alarm_item_id", 0) ?: return
        service.showNotification()
    }
}