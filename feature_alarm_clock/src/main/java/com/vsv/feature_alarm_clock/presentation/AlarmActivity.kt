package com.vsv.feature_alarm_clock.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.vsv.core.extensions.turnScreenOffAndKeyguardOn
import com.vsv.core.extensions.turnScreenOnAndKeyguardOff
import com.vsv.core_ui.theme.RuleYourTimeTheme
import com.vsv.feature_alarm_clock.data.alarm_clock.foreground_services.AlarmService
import com.vsv.feature_alarm_clock.data.alarm_clock.foreground_services.AlarmServiceCommands

class AlarmActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        turnScreenOnAndKeyguardOff()
        setContent {
            RuleYourTimeTheme {
                Button(
                    onClick = {
                        Intent(this, AlarmService::class.java).also{
                            it.action = AlarmServiceCommands.STOP.toString()
                            startService(it)
                        }
                        finishAndRemoveTask()
                    }
                ) {
                    Text(text = "finish")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        turnScreenOffAndKeyguardOn()
    }
}