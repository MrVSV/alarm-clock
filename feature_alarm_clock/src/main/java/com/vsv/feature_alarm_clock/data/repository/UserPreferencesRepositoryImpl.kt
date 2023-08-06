package com.vsv.feature_alarm_clock.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.vsv.feature_alarm_clock.domain.preferences.UserPreferences
import com.vsv.feature_alarm_clock.domain.repository.UserPreferencesRepository
import com.vsv.feature_alarm_clock.domain.repository.UserPreferencesRepository.Companion.IS_ALARM_RATIONALE_SHOWN
import com.vsv.feature_alarm_clock.domain.repository.UserPreferencesRepository.Companion.IS_NOTIFICATION_RATIONALE_SHOWN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : UserPreferencesRepository {

    override suspend fun <T> saveToPreferences(preferencesKey: Preferences.Key<T>, value: T) {
        dataStore.edit { prefs ->
            prefs[preferencesKey] = value
        }
    }

    override fun getFromPreferences(): Flow<UserPreferences> {
        return dataStore.data.map { prefs ->
            UserPreferences(
                isAlarmRationaleShown = prefs[IS_ALARM_RATIONALE_SHOWN] ?: false,
                isNotificationRationaleShown = prefs[IS_NOTIFICATION_RATIONALE_SHOWN] ?: false
            )
        }
    }
}