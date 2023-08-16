package com.vsv.ruleyourtime.receivers

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vsv.core.data.AlarmScheduler.Companion.ALARM_ITEM_ID
import com.vsv.ruleyourtime.service.AlarmService
import com.vsv.ruleyourtime.service.AlarmServiceCommands

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val itemId = intent.getIntExtra(ALARM_ITEM_ID, 0)
        Log.d(TAG, "onReceive: extr ${intent.extras}")
        Intent(context, AlarmService::class.java).apply {
            action = AlarmServiceCommands.START.toString()
            putExtra(ALARM_ITEM_ID, itemId)
            context.startForegroundService(this)
        }
    }
}