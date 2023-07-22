package com.vsv.ruleyourtime.alarmclock

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vsv.ruleyourtime.utils.parcelable

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val item = intent?.parcelable<AlarmItem>("alarm_item") ?: return
        Log.d(TAG, "onReceive: ${item.hashCode()}")
        Log.d(TAG, "onReceive: itm $item")
        Log.d(TAG, "onReceive: extr ${intent.extras}")
        Intent(context, AlarmService::class.java).also{
            it.action = AlarmServiceCommands.START.toString()
            it.putExtra("alarm_item", item)
            context.startForegroundService(it)
        }
    }
}