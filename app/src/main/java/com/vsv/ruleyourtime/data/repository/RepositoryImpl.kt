package com.vsv.ruleyourtime.data.repository

import com.vsv.ruleyourtime.data.local_db.AlarmsDao
import com.vsv.ruleyourtime.domain.model.AlarmItem
import com.vsv.ruleyourtime.domain.repository.Repository
import com.vsv.ruleyourtime.domain.alarm_clock.Scheduler
import com.vsv.ruleyourtime.utils.toListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val alarmsDao: AlarmsDao,
    private val alarmScheduler: Scheduler,
) : Repository {

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

    override suspend fun addAlarm(alarmItem: AlarmItem): Boolean {
        return try {
            alarmScheduler.schedule(alarmItem.toEntity())
            alarmsDao.addAlarm(alarmItem.toEntity())
            true
        } catch (e: SecurityException) {
            false
        }
    }

    override fun getAlarmList(): Flow<List<AlarmItem>> {
        return alarmsDao.getAlarmsList().map { listEntity ->
            listEntity.toListModel()
        }
    }

    private suspend fun scheduleAlarm() {
        val entity = alarmsDao.getLastUpdatedAlarm()
        alarmScheduler.schedule(entity)
    }
}