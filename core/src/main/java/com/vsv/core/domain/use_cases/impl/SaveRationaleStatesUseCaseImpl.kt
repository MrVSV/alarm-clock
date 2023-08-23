package com.vsv.core.domain.use_cases.impl

import androidx.datastore.preferences.core.Preferences
import com.vsv.core.domain.permission_state.RationaleStateRepository
import com.vsv.core.domain.use_cases.interfaces.SaveRationaleStatesUseCase

class SaveRationaleStatesUseCaseImpl(
    private val rationaleStateRepository: RationaleStateRepository,
) : SaveRationaleStatesUseCase {

    override suspend operator fun <T> invoke(preferencesKey: Preferences.Key<T>, value: T) {
        rationaleStateRepository.saveToPreferences(preferencesKey, value)
    }
}