package com.vsv.feature_alarm_clock.di

import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {

    single {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().dataStoreFile("user_prefs.preferences_pb") }
        )
    }

}