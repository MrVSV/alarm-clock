package com.vsv.core.domain.navigation

sealed class Destination (val route: String) {

    object AlarmsScreen: Destination("alarm_screen")
    object RingtonePickerScreen: Destination("ringtone_picker_screen")

}