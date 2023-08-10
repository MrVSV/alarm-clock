package com.vsv.feature_alarm_clock.data.repository

import com.google.common.truth.Truth.assertThat
import com.vsv.core.domain.ScheduleResult
import com.vsv.core.domain.Scheduler
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.local_data_base.data_base.AlarmsDao
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class RepositoryTest {

    private val scheduler = mock<Scheduler>()
    private val dao = mock<AlarmsDao>()

    @Test
    fun schedule_correct_alarm() {
        val repo = RepositoryImpl(dao, scheduler)
        val alarm = AlarmItem(isEnabled = true, hours = 12, minutes = 30)
        runBlocking {
            repo.addAlarm(alarm)
            verify(scheduler, times(1)).schedule(dao.getLastUpdatedAlarm())
            assertThat(repo.addAlarm(alarm)).isEqualTo(ScheduleResult.Success)
        }
    }
}