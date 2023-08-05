package com.vsv.ruleyourtime.domain.repository

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.vsv.ruleyourtime.domain.preferences.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    suspend fun <T> saveToPreferences(preferencesKey: Preferences.Key<T>, value: T)

    fun getFromPreferences(): Flow<UserPreferences>

    companion object {
        val IS_ALARM_RATIONALE_SHOWN = booleanPreferencesKey("is_alarm_rationale_shown")
        val IS_NOTIFICATION_RATIONALE_SHOWN =
            booleanPreferencesKey("is_notification_rationale_shown")
    }
}