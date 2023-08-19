package com.vsv.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.vsv.core.domain.UserPreferences
import com.vsv.core.domain.UserPreferencesRepository
import com.vsv.core.domain.UserPreferencesRepository.Companion.IS_ALARM_RATIONALE_SHOWN
import com.vsv.core.domain.UserPreferencesRepository.Companion.IS_NOTIFICATION_RATIONALE_SHOWN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : UserPreferencesRepository {

    override suspend fun <T> saveToPreferences(preferencesKey: Preferences.Key<T>, value: T) {
        val key =

        dataStore.edit { prefs ->
            prefs[preferencesKey] = value
        }
    }

    override fun getFromPreferences(): Flow<UserPreferences> {
        return dataStore.data.map { prefs ->
            UserPreferences(
                isAlarmRationaleShown = prefs[IS_ALARM_RATIONALE_SHOWN] ?: false,
                isNotificationRationaleShown = prefs[IS_NOTIFICATION_RATIONALE_SHOWN] ?: false,
            )
        }
    }
}