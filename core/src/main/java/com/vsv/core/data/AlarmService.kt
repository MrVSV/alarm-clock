package com.vsv.core.data

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.IBinder
import android.util.Log
import com.vsv.core.data.AlarmScheduler.Companion.ALARM_ITEM_ID
import com.vsv.core.domain.AppNotification
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class AlarmService : Service() {

    private val notification: AppNotification by inject(qualifier = named("alarmNotification"))

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
            setDataSource(
                this@AlarmService,
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            )
            setAudioAttributes(audioAttributes)
            isLooping = true
            prepare()
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val itemId = intent.getIntExtra(ALARM_ITEM_ID, 0)
        startForeground(1, notification.getNotification(itemId))
        when (intent.action) {
            AlarmServiceCommands.START.toString() -> mediaPlayer.start()
            AlarmServiceCommands.STOP.toString() -> {
                Log.d(TAG, "onStartCommand: stop")
                stopSelf()
            }
        }
        Log.d(TAG, "onStartCommand: $itemId")
        Log.d(TAG, "onStartCommand: itm ${itemId.hashCode()}")
        Log.d(TAG, "onStartCommand: ${intent.extras}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}