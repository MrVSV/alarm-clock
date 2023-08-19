package com.vsv.core.utils

sealed class Destination (val route: String) {

    object AlarmsScreen: Destination("alarm_screen")
    object RingtonePickerScreen: Destination("ringtone_picker_screen")

}