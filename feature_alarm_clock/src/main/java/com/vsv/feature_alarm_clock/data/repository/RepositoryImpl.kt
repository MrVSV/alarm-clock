package com.vsv.feature_alarm_clock.data.repository

import android.net.Uri
import com.vsv.core.domain.ringtone.MyRingtone
import com.vsv.core.domain.ringtone.RingtonePicker
import com.vsv.core.domain.scheduler.ScheduleResult
import com.vsv.core.domain.scheduler.Scheduler
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.model.toModel
import com.vsv.feature_alarm_clock.domain.repository.Repository
import com.vsv.feature_alarm_clock.utils.toAlarmModelList
import com.vsv.feature_alarm_clock.utils.toMyRingtoneModelList
import com.vsv.local_data_base.data_base.AlarmsDao
import com.vsv.local_data_base.data_base.UserRingtoneDao
import com.vsv.local_data_base.data_base.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val alarmsDao: AlarmsDao,
    private val alarmScheduler: Scheduler,
    private val ringtonePicker: RingtonePicker,
    private val userRingtoneDao: UserRingtoneDao,
) : Repository {

    override suspend fun addUserRingtone(uri: Uri) {
        userRingtoneDao.addUserRingtone(ringtonePicker.addUserRingtone(uri).toEntity())
    }

//    override suspend fun deleteUserRingtone(ringtone: MyRingtone) {
//        if (ringtonePicker.deleteUserRingtone(ringtone)) {
//            userRingtoneDao.deleteUserRingtone(ringtone.toEntity())
//            val defaultRingtone = ringtonePicker.getRingtonesList().first()
//            val alarms = alarmsDao.getAlarmByRingtoneUri(ringtone.uri)
//            Log.d(TAG, "deleteUserRingtone: $alarms")
//            alarms.forEach { alarm ->
//                alarmsDao.upsertAlarm(
//                    alarm.copy(
//                        ringtoneTitle = defaultRingtone.title,
//                        ringtoneUri = defaultRingtone.uri
//                    )
//                )
//            }
//        }
//    }

    override suspend fun getLastPickedRingtone(): MyRingtone {
        return ringtonePicker.getLastPickedRingtone()
    }

    override fun getUserRingtonesList(): Flow<List<MyRingtone>> {
        return userRingtoneDao.getUserRingtoneList().map { entity ->
            entity.toMyRingtoneModelList()
        }
    }

    override suspend fun getDeviceRingtonesList(): List<MyRingtone> {
        return ringtonePicker.getRingtonesList()
    }

    override suspend fun setRingtone(alarmItem: AlarmItem, myRingtone: MyRingtone) {
        alarmsDao.upsertAlarm(
            alarmItem.toEntity().copy(
                ringtoneTitle = myRingtone.title,
                ringtoneUri = myRingtone.uri
            )
        )
        ringtonePicker.setRingtoneAsLastPicked(myRingtone)
    }

    override suspend fun scheduleAlarm(alarmItem: AlarmItem): ScheduleResult {
        alarmsDao.upsertAlarm(alarmItem.toEntity())
        return alarmScheduler.schedule(alarmsDao.getLastUpdatedAlarm())
    }

    override suspend fun updateAlarm(alarmItem: AlarmItem) {
        alarmsDao.upsertAlarm(alarmItem.toEntity())
    }

    override suspend fun getAlarmById(alarmItemId: Int): AlarmItem {
        return alarmsDao.getAlarmById(alarmItemId).toModel()
    }

    override fun resetAllAlarms() {
        var job: Job? = null
        job = CoroutineScope(Dispatchers.IO).launch {
            alarmsDao.getAlarmsForReschedule().forEach {
                alarmScheduler.schedule(it)
            }
            job?.cancel()
        }
    }

    override suspend fun deleteAlarm(alarmItem: AlarmItem) {
        alarmsDao.deleteAlarm(alarmItem.toEntity())
        alarmScheduler.cancel(alarmItem.toEntity())
    }

    override suspend fun disableAlarm(alarmItem: AlarmItem) {
        alarmsDao.upsertAlarm(alarmItem.toEntity())
        alarmScheduler.cancel(alarmItem.toEntity())
    }

    override fun getAlarmList(): Flow<List<AlarmItem>> {
        return alarmsDao.getAlarmsList().map { listEntity ->
            listEntity.toAlarmModelList()
        }
    }
}