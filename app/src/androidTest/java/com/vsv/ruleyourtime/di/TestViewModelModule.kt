package com.vsv.ruleyourtime.di

import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testViewModelModule = module {

    viewModel {
        AlarmsScreenViewModel(
            repository = get(),
            userPreferencesRepository = get()
        )
    }
}