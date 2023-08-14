package com.vsv.ruleyourtime.alarms_screen

import android.os.Build
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreen
import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreenViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.koinViewModel


class AlarmsScreenTest{

    @get:Rule(order = 0)
    val permissionRule: GrantPermissionRule? =
        GrantPermissionRule.grant(android.Manifest.permission.POST_NOTIFICATIONS)

    @get:Rule(order = 2)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            val viewModel: AlarmsScreenViewModel = koinViewModel()
            val navController = rememberNavController()
            AlarmsScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = viewModel::onEvent,
                navController = navController,
            )
        }
    }

    @Test
    fun firstFabClick() {
        composeRule.onNodeWithContentDescription("add alarm").performClick()
//        grandPermission()
        composeRule.onAllNodes(isDialog()).onFirst().assertIsDisplayed()
        composeRule.onNodeWithText("Confirm").performClick()
        composeRule.onNodeWithTag("alarm_item").assertIsDisplayed()

    }

    private fun grandPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            println("contentvalues UIAnimator")
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            val allowPermission = device.findObject(UiSelector().text("Allow"))
            if (allowPermission.exists()) {
                println("allowPermission exists")
                allowPermission.click()
            } else println("allowPermission doesn't exist")
        }
    }
}