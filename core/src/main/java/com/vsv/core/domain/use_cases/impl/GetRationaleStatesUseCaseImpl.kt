package com.vsv.core.domain.use_cases.impl

import com.vsv.core.domain.permission_state.RationaleState
import com.vsv.core.domain.permission_state.RationaleStateRepository
import com.vsv.core.domain.use_cases.interfaces.GetRationaleStatesUseCase
import kotlinx.coroutines.flow.Flow

class GetRationaleStatesUseCaseImpl(
    private val rationaleStateRepository: RationaleStateRepository,
) : GetRationaleStatesUseCase {

    override operator fun invoke(): Flow<RationaleState> {
        return rationaleStateRepository.getFromPreferences()
    }
}