package com.vsv.feature_alarm_clock.di

import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        AlarmsScreenViewModel(
            repository = get(),
            userPreferencesRepository = get()
        )
    }
}