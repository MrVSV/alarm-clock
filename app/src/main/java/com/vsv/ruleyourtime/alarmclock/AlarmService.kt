package com.vsv.ruleyourtime.alarmclock

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.IBinder
import android.util.Log
import com.vsv.ruleyourtime.utils.parcelable

class AlarmService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val audioAttributes = AudioAttributes.Builder(
        )
            .setUsage(AudioAttributes.USAGE_ALARM)
            .setLegacyStreamType(AudioManager.STREAM_ALARM)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(this@AlarmService, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
            setAudioAttributes(audioAttributes)
            isLooping = true
            prepare()
        }
        Log.d(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notification = AlarmNotification(this)
        val item = intent.parcelable<AlarmItem>("alarm_item")
        if (item != null) {
            startForeground(1, notification.showNotification(item))
        }
        when (intent.action) {
            AlarmServiceCommands.START.toString() -> mediaPlayer.start()
            AlarmServiceCommands.STOP.toString() -> {
                Log.d(TAG, "onStartCommand: stop")
                stopSelf()
            }
        }
        Log.d(TAG, "onStartCommand: $item")
        Log.d(TAG, "onStartCommand: itm ${item.hashCode()}")
        Log.d(TAG, "onStartCommand: ${intent.extras}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}