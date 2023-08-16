package com.vsv.ruleyourtime.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.vsv.core.data.AlarmScheduler
import com.vsv.core.extensions.turnScreenOffAndKeyguardOn
import com.vsv.core.extensions.turnScreenOnAndKeyguardOff
import com.vsv.core_ui.theme.RuleYourTimeTheme
import com.vsv.ruleyourtime.service.AlarmService
import com.vsv.ruleyourtime.service.AlarmServiceCommands

class AlarmActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        turnScreenOnAndKeyguardOff()
        setContent {
            RuleYourTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                    ) {
                        Button(
                            onClick = {
                                Intent(this@AlarmActivity, AlarmService::class.java).apply {
                                    action = AlarmServiceCommands.STOP.toString()
                                    putExtra(AlarmScheduler.ALARM_ITEM_ID, intent.getIntExtra(
                                        AlarmScheduler.ALARM_ITEM_ID, 0)
                                    )
                                    startService(this)
                                }
                                finishAndRemoveTask()
                            }
                        ) {
                            Text(text = "finish")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        turnScreenOffAndKeyguardOn()
    }
}