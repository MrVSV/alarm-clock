package com.vsv.ruleyourtime.di

import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val testDataStoreModule = module {

    single {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().dataStoreFile("user_prefs.preferences_pb") }
        )
    }

}