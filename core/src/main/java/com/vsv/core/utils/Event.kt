package com.vsv.core.utils

interface Event {

    data class CheckAlarmPermissionState(val isGranted: Boolean): Event
    data class CheckNotificationPermissionState(val isGranted: Boolean): Event

}