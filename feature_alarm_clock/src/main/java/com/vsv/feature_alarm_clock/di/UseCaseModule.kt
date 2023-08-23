package com.vsv.feature_alarm_clock.di

import com.vsv.feature_alarm_clock.domain.use_case.AlarmUseCases
import com.vsv.feature_alarm_clock.domain.use_case.RingtoneUseCases
import com.vsv.feature_alarm_clock.domain.use_case.impl.AddUserRingtoneUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.DeleteAlarmUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.DisableAlarmUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.EnableAlarmUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.GetAlarmsListUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.GetRingtoneUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.GetRingtonesListUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.ScheduleAlarmUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.SetRingtoneUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.AddUserRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DeleteAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DisableAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.EnableAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetAlarmsListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetRingtonesListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.ScheduleAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.SetRingtoneUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single {
        AlarmUseCases(
            scheduleAlarmUseCase = get(),
            deleteAlarmUseCase = get(),
            enableAlarmUseCase = get(),
            disableAlarmUseCase = get(),
            getAlarmsListUseCase = get()
        )
    }

    single<ScheduleAlarmUseCase> { ScheduleAlarmUseCaseImpl(repository = get()) }
    single<DeleteAlarmUseCase> { DeleteAlarmUseCaseImpl(repository = get()) }
    single<EnableAlarmUseCase> { EnableAlarmUseCaseImpl(repository = get()) }
    single<DisableAlarmUseCase> { DisableAlarmUseCaseImpl(repository = get()) }
    single<GetAlarmsListUseCase> { GetAlarmsListUseCaseImpl(repository = get()) }

    single {
        RingtoneUseCases(
            getRingtoneUseCase = get(),
            setRingtoneUseCase = get(),
            getRingtonesListUseCase = get(),
            addUserRingtoneUseCase = get()
        )
    }

    single<GetRingtoneUseCase> { GetRingtoneUseCaseImpl(repository = get()) }
    single<SetRingtoneUseCase> { SetRingtoneUseCaseImpl(repository = get()) }
    single<GetRingtonesListUseCase> { GetRingtonesListUseCaseImpl(repository = get()) }
    single<AddUserRingtoneUseCase> { AddUserRingtoneUseCaseImpl(repository = get()) }
}