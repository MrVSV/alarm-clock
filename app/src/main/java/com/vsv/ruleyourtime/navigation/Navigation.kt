package com.vsv.ruleyourtime.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vsv.ruleyourtime.ui.AlarmsScreen

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
            AlarmsScreen(
                navController = navController
            )
        }
    }
}