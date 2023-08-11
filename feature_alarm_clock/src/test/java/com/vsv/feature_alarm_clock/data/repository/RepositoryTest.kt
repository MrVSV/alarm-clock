package com.vsv.feature_alarm_clock.data.repository

import com.google.common.truth.Truth.assertThat
import com.vsv.core.domain.ScheduleResult
import com.vsv.feature_alarm_clock.domain.model.AlarmItem
import com.vsv.feature_alarm_clock.domain.repository.Repository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    private lateinit var fakeScheduler: FakeScheduler
    private lateinit var fakeDao: FakeAlarmsDao
    private lateinit var repository: Repository
    private val alarmItem1 = AlarmItem(id = 1, isEnabled = true, hours = 12, minutes = 30)

    @Before
    fun setUp() {
        fakeScheduler = FakeScheduler()
        fakeScheduler.canScheduleAlarm = true
        fakeDao = FakeAlarmsDao()
        repository = RepositoryImpl(fakeDao, fakeScheduler)

    }

    @Test
    fun `add alarm success`() {
        runTest {
            assertThat(repository.addAlarm(alarmItem1)).isEqualTo(ScheduleResult.Success)
        }
    }

    @Test
    fun `add alarm error`() {
        fakeScheduler.canScheduleAlarm = false
        runTest {
            assertThat(repository.addAlarm(alarmItem1)).isEqualTo(ScheduleResult.Error(fakeScheduler.exception))
        }
    }
}