package com.vsv.feature_alarm_clock.di

import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreenViewModel
import com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen.RingtonePickerScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        AlarmsScreenViewModel(
            alarmUseCases = get(),
            rationaleStateUseCases = get(),
            ringtoneUseCases = get()
        )
    }

    viewModel {
        RingtonePickerScreenViewModel(
            alarmUseCases = get(),
            ringtoneUseCases = get()
        )
    }
}