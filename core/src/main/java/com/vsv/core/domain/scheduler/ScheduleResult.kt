package com.vsv.core.domain.scheduler

sealed interface ScheduleResult{
    object Success: ScheduleResult
    data class Error(val exception: Exception): ScheduleResult
}