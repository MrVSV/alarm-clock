package com.vsv.feature_alarm_clock.di

import com.vsv.feature_alarm_clock.domain.use_case.AlarmUseCases
import com.vsv.feature_alarm_clock.domain.use_case.RingtoneUseCases
import com.vsv.feature_alarm_clock.domain.use_case.impl.AddUserRingtoneUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.DeleteAlarmUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.DeleteUserRingtoneUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.DisableAlarmUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.EnableAlarmUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.GetAlarmByIdUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.GetAlarmsListUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.GetDeviceRingtonesListUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.GetLastPickedRingtoneUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.GetUserRingtonesListUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.ScheduleAlarmUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.SetAlarmRepeatingUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.impl.SetRingtoneUseCaseImpl
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.AddUserRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DeleteAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DeleteUserRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.DisableAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.EnableAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetAlarmByIdUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetAlarmsListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetDeviceRingtonesListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetLastPickedRingtoneUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.GetUserRingtonesListUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.ScheduleAlarmUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.SetAlarmRepeatingUseCase
import com.vsv.feature_alarm_clock.domain.use_case.interfaces.SetRingtoneUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single {
        AlarmUseCases(
            scheduleAlarmUseCase = get(),
            deleteAlarmUseCase = get(),
            enableAlarmUseCase = get(),
            disableAlarmUseCase = get(),
            getAlarmsListUseCase = get(),
            getAlarmByIdUseCase = get(),
            setAlarmRepeatingUseCase = get()
        )
    }

    single<ScheduleAlarmUseCase> { ScheduleAlarmUseCaseImpl(repository = get()) }
    single<DeleteAlarmUseCase> { DeleteAlarmUseCaseImpl(repository = get()) }
    single<EnableAlarmUseCase> { EnableAlarmUseCaseImpl(repository = get()) }
    single<DisableAlarmUseCase> { DisableAlarmUseCaseImpl(repository = get()) }
    single<GetAlarmsListUseCase> { GetAlarmsListUseCaseImpl(repository = get()) }
    single<GetAlarmByIdUseCase> { GetAlarmByIdUseCaseImpl(repository = get()) }
    single<SetAlarmRepeatingUseCase> { SetAlarmRepeatingUseCaseImpl(repository = get()) }

    single {
        RingtoneUseCases(
            getLastPickedRingtoneUseCase = get(),
            setRingtoneUseCase = get(),
            getDeviceRingtonesListUseCase = get(),
            getUserRingtonesListUseCase = get(),
            addUserRingtoneUseCase = get(),
            deleteUserRingtoneUseCase = get(),
        )
    }

    single<GetLastPickedRingtoneUseCase> { GetLastPickedRingtoneUseCaseImpl(repository = get()) }
    single<SetRingtoneUseCase> { SetRingtoneUseCaseImpl(repository = get()) }
    single<GetDeviceRingtonesListUseCase> { GetDeviceRingtonesListUseCaseImpl(repository = get()) }
    single<GetUserRingtonesListUseCase> { GetUserRingtonesListUseCaseImpl(repository = get()) }
    single<AddUserRingtoneUseCase> { AddUserRingtoneUseCaseImpl(repository = get()) }
    single<DeleteUserRingtoneUseCase> { DeleteUserRingtoneUseCaseImpl(repository = get()) }
}