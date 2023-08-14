package com.vsv.core.data

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vsv.core.data.AlarmScheduler.Companion.ALARM_ITEM_ID

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val itemId = intent.getIntExtra(ALARM_ITEM_ID, 0)
        Log.d(TAG, "onReceive: extr ${intent.extras}")
        Intent(context, AlarmService::class.java).also{
            it.action = AlarmServiceCommands.START.toString()
            it.putExtra(ALARM_ITEM_ID, itemId)
            context.startForegroundService(it)
        }
    }
}