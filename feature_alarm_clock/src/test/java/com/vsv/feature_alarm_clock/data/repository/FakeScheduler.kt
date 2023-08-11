package com.vsv.feature_alarm_clock.data.repository

import com.vsv.core.domain.Item
import com.vsv.core.domain.ScheduleResult
import com.vsv.core.domain.Scheduler
import com.vsv.local_data_base.data_base.AlarmItemEntity
import kotlin.properties.Delegates

class FakeScheduler : Scheduler {

    val exception = Exception("can't schedule alarm")
    var canScheduleAlarm by Delegates.notNull<Boolean>()
    private val alarmsList = mutableListOf<AlarmItemEntity>()


    override fun schedule(item: Item): ScheduleResult {
        return if (canScheduleAlarm) {
            alarmsList.add(item as AlarmItemEntity)
            ScheduleResult.Success
        } else {
            ScheduleResult.Error(exception = exception)
        }
    }

    override fun disable(item: Item) {
        TODO("Not yet implemented")
    }

    override fun cancel(item: Item) {
        if (alarmsList.isNotEmpty())
            alarmsList.remove(item as AlarmItemEntity)
    }
}