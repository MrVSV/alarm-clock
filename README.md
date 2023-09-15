# Alarm clock app
work in progress

## Technology stack

 - UI by JetpackCompose
 - AlarmManager
 - RingtonePicker
 - ForegroundService
 - BroadcastReceiver
 - Room database
 - Modularization
 - Koin
 - MVVM

## Features

 - Set a one-time alarm
 - Set an alarm that repeats on certain days of the week
 - Select an alarm tone from standard device signals, as well as from custom audio files
 - Disable/delete alarm

## How it works

 - Setting alarm by AlarmManager.setAlarmClock()
 - Alarms are saved in the Room database
 - ForegroundService is used to notify the user about the triggered alarm
 - Since all alarms are deleted when the device is turned off, BroadcastReceiver is used to reset the alarms after the device is turned on. The same goes for revoking the  SCHEDULE_EXACT_ALARM permission for Android 12
 - A custom RingtonePicker is used to set the ringtone
 - MediaPlayer is used to play the ringtone
 - Checking POST_NOTIFICATION and SCHEDULE_EXACT_ALARM permissions

## Pictures

![studio6400004311](https://github.com/MrVSV/alarm-clock/assets/99773044/8fae2378-9185-4a50-b660-8221385baa74)
![studio6400004312](https://github.com/MrVSV/alarm-clock/assets/99773044/0b60a88f-38af-4f9e-9bcf-f1bd621b5c86)
![studio6400004313](https://github.com/MrVSV/alarm-clock/assets/99773044/13050637-e5bb-4508-80b4-753ce0d2abfc)
![studio6400004314](https://github.com/MrVSV/alarm-clock/assets/99773044/df5c143c-0054-4019-9833-10dba603b000)
![studio6400004315](https://github.com/MrVSV/alarm-clock/assets/99773044/5e818863-77d0-49f9-8771-d0563edc49f3)
