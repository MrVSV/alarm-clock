package com.vsv.core.domain.use_cases.interfaces

import androidx.datastore.preferences.core.Preferences

interface SaveRationaleStatesUseCase {
    suspend operator fun <T> invoke(preferencesKey: Preferences.Key<T>, value: T)
}