package com.vsv.ruleyourtime.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vsv.ruleyourtime.data.alarm_clock.foreground_services.AlarmService
import com.vsv.ruleyourtime.data.alarm_clock.foreground_services.AlarmServiceCommands
import com.vsv.ruleyourtime.presentation.ui.theme.RuleYourTimeTheme
import com.vsv.ruleyourtime.utils.turnScreenOffAndKeyguardOn
import com.vsv.ruleyourtime.utils.turnScreenOnAndKeyguardOff

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RuleYourTimeTheme {
    }
}