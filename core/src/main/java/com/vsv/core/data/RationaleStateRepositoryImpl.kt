package com.vsv.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.vsv.core.domain.permission_state.RationaleState
import com.vsv.core.domain.permission_state.RationaleStateRepository
import com.vsv.core.domain.permission_state.RationaleStateRepository.Companion.IS_ALARM_RATIONALE_SHOWN
import com.vsv.core.domain.permission_state.RationaleStateRepository.Companion.IS_NOTIFICATION_RATIONALE_SHOWN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RationaleStateRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : RationaleStateRepository {

    override suspend fun <T> saveToPreferences(preferencesKey: Preferences.Key<T>, value: T) {
        dataStore.edit { prefs ->
            prefs[preferencesKey] = value
        }
    }

    override fun getFromPreferences(): Flow<RationaleState> {
        return dataStore.data.map { prefs ->
            RationaleState(
                isAlarmRationaleShown = prefs[IS_ALARM_RATIONALE_SHOWN] ?: false,
                isNotificationRationaleShown = prefs[IS_NOTIFICATION_RATIONALE_SHOWN] ?: false,
            )
        }
    }
}