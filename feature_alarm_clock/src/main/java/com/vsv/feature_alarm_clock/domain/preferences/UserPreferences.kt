package com.vsv.feature_alarm_clock.domain.preferences

data class UserPreferences(
    val isAlarmRationaleShown: Boolean = false,
    val isNotificationRationaleShown: Boolean = false
)
