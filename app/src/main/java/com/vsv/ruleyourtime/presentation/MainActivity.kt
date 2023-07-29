package com.vsv.ruleyourtime.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vsv.ruleyourtime.navigation.Navigation
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RuleYourTimeTheme {
        RuleYourTimeApp()
    }
}
