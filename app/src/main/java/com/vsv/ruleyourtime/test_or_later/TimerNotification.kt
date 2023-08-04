package com.vsv.ruleyourtime.test_or_later

//class TimerNotification(
//    private val context: Context
//): AppNotification {
//
//    override fun createNotificationChanel(): NotificationChannelCompat {
//        Log.d(ContentValues.TAG, "createNotificationChanel: timer")
//        return NotificationChannelCompat.Builder(
//            TIMER_CHANNEL_ID,
//            NotificationManager.IMPORTANCE_MAX
//        )
//            .setName("Timer Alarms")
//            .setSound(null, null)
//            .setVibrationEnabled(true)
//            .build()
//
////        val notificationManager = NotificationManagerCompat.from(context)
////        notificationManager.createNotificationChannel(channel)
//    }
//
//    override fun getNotification(itemId: Int): Notification {
//        val activityIntent = Intent(context, AlarmActivity::class.java)
//        val pendingIntent =
//            PendingIntent.getActivity(
//                context,
//                0,
//                activityIntent,
//                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//            )
//        val alarmServiceIntent = Intent(context, AlarmService::class.java).apply {
//            action = AlarmServiceCommands.STOP.toString()
//        }
//        val alarmSoundPendingIntent =
//            PendingIntent.getService(
//                context,
//                0,
//                alarmServiceIntent,
//                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//            )
//        val action = NotificationCompat.Action(null, "Stop", alarmSoundPendingIntent)
//
//        return NotificationCompat.Builder(context, TIMER_CHANNEL_ID)
//            .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle("Alarm Clock #$itemId")
////            .setContentText("Time ${convertAlarmTime(item.alarmTimeMillis)}")
//            .setFullScreenIntent(pendingIntent, true)
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setCategory(NotificationCompat.CATEGORY_ALARM)
//            .addAction(action)
//            .build()
//    }
//
//    companion object {
//        const val TIMER_CHANNEL_ID = "timer_channel_id"
//    }
//}