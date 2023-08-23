package com.vsv.core.domain.permission_state

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow

interface RationaleStateRepository {

    suspend fun <T> saveToPreferences(preferencesKey: Preferences.Key<T>, value: T)

    fun getFromPreferences(): Flow<RationaleState>

    companion object {
        val IS_ALARM_RATIONALE_SHOWN = booleanPreferencesKey("is_alarm_rationale_shown")
        val IS_NOTIFICATION_RATIONALE_SHOWN =
            booleanPreferencesKey("is_notification_rationale_shown")
    }
}