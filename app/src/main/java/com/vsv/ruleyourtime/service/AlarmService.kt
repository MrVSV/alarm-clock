package com.vsv.ruleyourtime.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import com.vsv.core.data.AlarmScheduler.Companion.ALARM_ITEM_ID
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.ruleyourtime.notifications.AppNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class AlarmService : Service() {

    private val notification: AppNotification by inject(qualifier = named("alarmNotification"))
    private val repository: Repository by inject()
    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        prepareMediaPlayer()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        var itemId = intent.getIntExtra(ALARM_ITEM_ID, 0)
        var job: Job? = null
        startForeground(1, notification.createNotification(itemId))
        when (intent.action) {
            AlarmServiceCommands.START.toString() -> {
                itemId = intent.getIntExtra(ALARM_ITEM_ID, 0)
                job = CoroutineScope(Dispatchers.IO).launch {
                    val alarm = repository.getAlarmById(itemId)
                    job?.cancel()
                    mediaPlayer.apply {
                        setDataSource(
                            this@AlarmService,
                            Uri.parse(alarm.ringtone.uri)
                        )
                        prepare()
                        start()
                    }
                }
            }
            AlarmServiceCommands.STOP.toString() -> {
                itemId = intent.getIntExtra(ALARM_ITEM_ID, 0)
                job = CoroutineScope(Dispatchers.IO).launch {
                    val alarm = repository.getAlarmById(itemId)
//                    if (alarm.alarmDays.any { it }){
//
//                    }
                    repository.updateAlarm(alarm.copy(isEnabled = false))
                    job?.cancel()
                }
                stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun prepareMediaPlayer() {
        val audioAttributes = AudioAttributes.Builder(
        )
            .setUsage(AudioAttributes.USAGE_ALARM)
            .setLegacyStreamType(AudioManager.STREAM_ALARM)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        mediaPlayer = MediaPlayer().apply {

            setAudioAttributes(audioAttributes)
            isLooping = true

        }
    }
}