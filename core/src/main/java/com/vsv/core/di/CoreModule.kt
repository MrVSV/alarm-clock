package com.vsv.core.di

import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.vsv.core.data.AlarmScheduler
import com.vsv.core.data.RingtonePickerImpl
import com.vsv.core.data.UserPreferencesRepositoryImpl
import com.vsv.core.domain.RingtonePicker
import com.vsv.core.domain.Scheduler
import com.vsv.core.domain.UserPreferencesRepository
import com.vsv.core.utils.MyCalendar
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {

//    single<AppNotification>(qualifier = named("timerNotification")) { TimerNotification(context = get()) }
    single<RingtonePicker> { RingtonePickerImpl(context = get()) }

    single<Scheduler> { AlarmScheduler(context = get(), calendar = get()) }

    single { MyCalendar() }

    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(dataStore = get()) }

    single {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().dataStoreFile("user_prefs.preferences_pb") }
        )
    }

}