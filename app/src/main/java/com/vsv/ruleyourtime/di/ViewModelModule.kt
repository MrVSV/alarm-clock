package com.vsv.ruleyourtime.di

import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AlarmsScreenViewModel(repository = get()) }

}