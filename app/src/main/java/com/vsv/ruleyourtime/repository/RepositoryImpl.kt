package com.vsv.ruleyourtime.repository

import com.vsv.ruleyourtime.alarmclock.AlarmItem
import com.vsv.ruleyourtime.alarmclock.Scheduler
import com.vsv.ruleyourtime.localdb.AlarmsDao
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

    override suspend fun addAlarm(alarmItem: AlarmItem) {
        alarmsDao.addAlarm(alarmItem.toEntity())
        scheduleAlarm()
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