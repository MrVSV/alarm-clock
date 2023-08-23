package com.vsv.core.domain.use_cases.interfaces

import com.vsv.core.domain.permission_state.RationaleState
import kotlinx.coroutines.flow.Flow

interface GetRationaleStatesUseCase {
    operator fun invoke(): Flow<RationaleState>
}