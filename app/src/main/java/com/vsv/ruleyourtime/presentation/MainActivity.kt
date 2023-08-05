package com.vsv.ruleyourtime.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.vsv.ruleyourtime.presentation.navigation.Navigation
import com.vsv.ruleyourtime.presentation.ui.theme.RuleYourTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuleYourTimeTheme {
                RuleYourTimeApp()
            }
        }
    }
}

@Composable
fun RuleYourTimeApp(
){
    Navigation()
}

