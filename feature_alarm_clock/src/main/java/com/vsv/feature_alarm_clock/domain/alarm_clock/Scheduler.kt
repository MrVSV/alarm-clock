package com.vsv.feature_alarm_clock.domain.alarm_clock

import com.vsv.local_data_base.data_base.AlarmItemEntity

interface Scheduler {

    fun schedule(entity: AlarmItemEntity)

    fun disable(entity: AlarmItemEntity)

    fun cancel(entity: AlarmItemEntity)
}