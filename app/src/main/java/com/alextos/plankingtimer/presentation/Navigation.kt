package com.alextos.plankingtimer.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alextos.plankingtimer.domain.model.Timer
import com.alextos.plankingtimer.presentation.screens.launch_screen.LaunchScreen
import com.alextos.plankingtimer.presentation.screens.main.MainScreen
import com.alextos.plankingtimer.presentation.screens.timer.TimerScreen
import java.util.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val timers = remember {
        mutableListOf<Timer>()
    }

    NavHost(navController = navController, startDestination = Screen.LaunchScreen.route) {
        composable(route = Screen.LaunchScreen.route) {
            LaunchScreen {
                navController.navigate(route = Screen.MainScreen.route)
            }
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen { timerQueue ->
                timers.addAll(timerQueue.timers)
                timers.removeFirstOrNull()?.let { timer ->
                    navController.navigate(route = Screen.TimerScreen.routeWithArgs(
                        timer.id.toString(),
                        timer.name,
                        timer.secondsCount.toString()
                    ))
                }
            }
        }

        composable(
            route = Screen.TimerScreen.route
                    + "/{id}"
                    + "/{name}"
                    + "/{seconds}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                },
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("seconds") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = UUID.fromString(entry.arguments?.getString("id") ?: "")
            val name = entry.arguments?.getString("name") ?: ""
            val secondsNumber = entry.arguments?.getInt("seconds") ?: 0
            val timer = Timer(id, name, secondsNumber)

            TimerScreen(
                timer = timer,
                onTimerStopped = {
                    timers.clear()
                    navController.popBackStack()
                },
                onTimerFinished = {
                    if (timers.isEmpty()) {
                        navController.popBackStack(Screen.MainScreen.route, inclusive = false)
                    } else {
                        timers.removeFirstOrNull()?.let { timer ->
                            navController.navigate(route = Screen.TimerScreen.routeWithArgs(
                                timer.id.toString(),
                                timer.name,
                                timer.secondsCount.toString()
                            )) {
                                popUpTo(Screen.MainScreen.route)
                            }
                        }
                    }
                }
            )
        }
    }
}