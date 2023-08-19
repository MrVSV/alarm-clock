package com.vsv.ruleyourtime.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vsv.core.utils.Destination
import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreen
import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreenViewModel
import com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen.RingtonePickerScreen
import com.vsv.feature_alarm_clock.presentation.ringtone_picker_screen.RingtonePickerScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
) {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.AlarmsScreen.route,
        modifier = Modifier
    ) {
        composable(
            route = Destination.AlarmsScreen.route
        ) {
            val viewModel: AlarmsScreenViewModel = koinViewModel()
            AlarmsScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = viewModel::onEvent,
                navController = navController,
            )
        }
        composable(
            route = Destination.RingtonePickerScreen.route + "/{alarmId}",
            arguments = listOf(
                navArgument(name = "alarmId") {
                    type = NavType.IntType

                }
            )
        ) {
            val viewModel: RingtonePickerScreenViewModel = koinViewModel()
            RingtonePickerScreen(
                alarmItemId = it.arguments?.getInt("alarmId") ?: 0,
                state = viewModel.state.collectAsState().value,
                onEvent = viewModel::onEvent,
                navController = navController,
            )
        }
    }
}