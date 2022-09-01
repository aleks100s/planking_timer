package com.alextos.plankingtimer.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alextos.plankingtimer.domain.model.Timer
import com.alextos.plankingtimer.domain.model.TimerQueue
import com.alextos.plankingtimer.presentation.screens.launch_screen.LaunchScreen
import com.alextos.plankingtimer.presentation.screens.main.MainScreen
import com.alextos.plankingtimer.presentation.screens.timer.TimerScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LaunchScreen.route) {
        composable(route = Screen.LaunchScreen.route) {
            LaunchScreen {
                navController.navigate(route = Screen.MainScreen.route)
            }
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen { timer ->
                navController.navigate(route = Screen.TimerScreen.routeWithArgs(
                    timer.title,
                    timer.totalSeconds().toString()
                ))
            }
        }

        composable(
            route = Screen.TimerScreen.route
                    + "/{name}"
                    + "/{seconds}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("seconds") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            TimerScreen(
                timerName = entry.arguments?.getString("name") ?: "",
                secondsNumber = entry.arguments?.getInt("seconds") ?: 0,
                onTimerStopped = {
                    navController.popBackStack()
                },
                onTimerFinished = {
                    navController.popBackStack()
                }
            )
        }
    }
}