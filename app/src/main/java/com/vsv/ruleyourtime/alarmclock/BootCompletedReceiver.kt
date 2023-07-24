package com.vsv.ruleyourtime.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vsv.ruleyourtime.repository.Repository
import org.koin.java.KoinJavaComponent.inject

class BootCompletedReceiver: BroadcastReceiver() {

    private val repository: Repository by inject(Repository::class.java)

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_LOCKED_BOOT_COMPLETED) {
            println("onReceive: BOOT_COMPLETED")
           repository.resetAllAlarms()
        }
    }
}