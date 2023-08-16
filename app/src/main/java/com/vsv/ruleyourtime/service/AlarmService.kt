package com.vsv.ruleyourtime.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.IBinder
import com.vsv.core.data.AlarmScheduler.Companion.ALARM_ITEM_ID
import com.vsv.local_data_base.data_base.AlarmsDao
import com.vsv.ruleyourtime.notifications.AppNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class AlarmService : Service() {

    private val notification: AppNotification by inject(qualifier = named("alarmNotification"))
    private val alarmsDao: AlarmsDao by inject()
    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        prepareMediaPlayer()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val itemId = intent.getIntExtra(ALARM_ITEM_ID, 0)
        var job: Job? = null
        startForeground(1, notification.createNotification(itemId))
        when (intent.action) {
            AlarmServiceCommands.START.toString() -> mediaPlayer.start()
            AlarmServiceCommands.STOP.toString() -> {
                job = CoroutineScope(Dispatchers.IO).launch {
                    alarmsDao.addAlarm(
                        alarmsDao.getAlarmById(intent.getIntExtra(ALARM_ITEM_ID, 0))
                            .copy(isEnabled = false)
                    )
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
            setDataSource(
                this@AlarmService,
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            )
            setAudioAttributes(audioAttributes)
            isLooping = true
            prepare()
        }
    }
}