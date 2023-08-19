package com.vsv.core.utils

interface Event {

    class CheckAlarmPermissionState(val isGranted: Boolean): Event
    class CheckNotificationPermissionState(val isGranted: Boolean): Event

}