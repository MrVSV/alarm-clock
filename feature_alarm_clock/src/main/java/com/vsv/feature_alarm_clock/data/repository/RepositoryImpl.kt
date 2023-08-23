package com.vsv.feature_alarm_clock.data.repository

import android.content.ContentValues.TAG
import android.util.Log
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

    override suspend fun addUserRingtone(myRingtone: MyRingtone) {
        userRingtoneDao.addUserRingtone(myRingtone.copy(isUserRingtone = true).toEntity())
    }

    override suspend fun getRingtone(): MyRingtone {
        return ringtonePicker.getRingtone()
    }

    override suspend fun getRingtonesList(): List<MyRingtone> {
        val deviceRingtones = ringtonePicker.getRingtonesList()
        val userRingtones = userRingtoneDao.getUserRingtoneList().toMyRingtoneModelList()
        Log.d(TAG, "device: ${deviceRingtones.size} user: ${userRingtones.size}")
        return deviceRingtones + userRingtones
    }

    override suspend fun setRingtone(alarmItemId: Int, myRingtone: MyRingtone) {
        val alarm = alarmsDao.getAlarmById(alarmItemId)
        alarmsDao.upsertAlarm(
            alarm.copy(
                ringtoneTitle = myRingtone.title,
                ringtoneUri = myRingtone.uri
            )
        )
        ringtonePicker.setRingtone(myRingtone)
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