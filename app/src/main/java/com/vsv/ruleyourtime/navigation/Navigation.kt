package com.vsv.ruleyourtime.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmsScreenViewModel
import com.vsv.ruleyourtime.presentation.alarms_screen.AlarmsScreen
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
            val viewModel = koinViewModel<AlarmsScreenViewModel>()
            val state = viewModel.state.collectAsState()
            val onEvent = viewModel::onEvent
            AlarmsScreen(
                navController = navController,
                state = state.value,
                onEvent = onEvent
            )
        }
    }
}