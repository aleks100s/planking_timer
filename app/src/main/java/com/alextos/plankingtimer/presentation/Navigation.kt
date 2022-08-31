package com.alextos.plankingtimer.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alextos.plankingtimer.presentation.screens.LaunchScreen
import com.alextos.plankingtimer.presentation.screens.timer.TimerScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LaunchScreen.route) {
        composable(route = Screen.LaunchScreen.route) {
            LaunchScreen(navController = navController)
        }
        composable(route = Screen.TimerScreen.route) {
            TimerScreen(60)
        }
    }
}