package com.vsv.ruleyourtime.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreen
import com.vsv.feature_alarm_clock.presentation.alarms_screen.AlarmsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
) {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.AlarmsScreen.name,
        modifier = Modifier
    ) {
        composable(
            route = Destinations.AlarmsScreen.name
        ) {
            val viewModel: AlarmsScreenViewModel = koinViewModel()
            AlarmsScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = viewModel::onEvent,
                navController = navController,
            )
        }
    }
}