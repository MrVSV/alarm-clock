package com.vsv.core.di

import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.vsv.core.data.AlarmScheduler
import com.vsv.core.data.RationaleStateRepositoryImpl
import com.vsv.core.data.RingtonePickerImpl
import com.vsv.core.domain.permission_state.RationaleStateRepository
import com.vsv.core.domain.ringtone.RingtonePicker
import com.vsv.core.domain.scheduler.Scheduler
import com.vsv.core.domain.use_cases.RationaleStateUseCases
import com.vsv.core.domain.use_cases.impl.GetRationaleStatesUseCaseImpl
import com.vsv.core.domain.use_cases.impl.SaveRationaleStatesUseCaseImpl
import com.vsv.core.domain.use_cases.interfaces.GetRationaleStatesUseCase
import com.vsv.core.domain.use_cases.interfaces.SaveRationaleStatesUseCase
import com.vsv.core.utils.MyCalendar
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {

//    single<AppNotification>(qualifier = named("timerNotification")) { TimerNotification(context = get()) }
    single<RingtonePicker> { RingtonePickerImpl(context = get()) }

    single<Scheduler> { AlarmScheduler(context = get(), calendar = get()) }

    single { MyCalendar() }

    single<RationaleStateRepository> { RationaleStateRepositoryImpl(dataStore = get()) }

    single {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().dataStoreFile("user_prefs.preferences_pb") }
        )
    }

    single{
        RationaleStateUseCases(
            saveRationaleStatesUseCase = get(),
            getRationaleStatesUseCase = get()
        )
    }

    single<SaveRationaleStatesUseCase> { SaveRationaleStatesUseCaseImpl(rationaleStateRepository = get()) }
    single<GetRationaleStatesUseCase> { GetRationaleStatesUseCaseImpl(rationaleStateRepository = get()) }




}