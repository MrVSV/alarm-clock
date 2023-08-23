package com.vsv.core.domain.use_cases

import com.vsv.core.domain.use_cases.interfaces.GetRationaleStatesUseCase
import com.vsv.core.domain.use_cases.interfaces.SaveRationaleStatesUseCase

data class RationaleStateUseCases(
    val saveRationaleStatesUseCase: SaveRationaleStatesUseCase,
    val getRationaleStatesUseCase: GetRationaleStatesUseCase
)
