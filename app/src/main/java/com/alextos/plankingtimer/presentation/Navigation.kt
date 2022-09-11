package com.alextos.plankingtimer.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alextos.plankingtimer.domain.model.main.Timer
import com.alextos.plankingtimer.presentation.screens.authentication.AuthenticationScreen
import com.alextos.plankingtimer.presentation.screens.create.CreateTimerScreen
import com.alextos.plankingtimer.presentation.screens.launch_screen.LaunchScreen
import com.alextos.plankingtimer.presentation.screens.main.MainScreen
import com.alextos.plankingtimer.presentation.screens.timer.TimerScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: NavigationViewModel = viewModel()

    val timers = viewModel.state.value

    NavHost(navController = navController, startDestination = Screen.LaunchScreen.route) {
        composable(route = Screen.LaunchScreen.route) {
            LaunchScreen(
                onAuthenticationNeeded = {
                    navController.navigate(route = Screen.AuthenticationScreen.route)
                },
                onLoadingFinished = {
                    navController.navigate(route = Screen.MainScreen.route)
                }
            )
        }

        composable(route = Screen.AuthenticationScreen.route) {
            AuthenticationScreen {
                navController.navigate(route = Screen.MainScreen.route)
            }
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(
                onTimerSelected = { timerQueue ->
                    viewModel.addTimers(timerQueue.timers ?: listOf())
                    viewModel.popTimer()?.let { timer ->
                        val route = Screen.TimerScreen.routeWithArgs(
                            timer.id.toString(),
                            timer.name.toString(),
                            timer.secondsCount.toString()
                        )
                        navController.navigate(route = route)
                    }
                },
                createNewTimer = {
                    navController.navigate(route = Screen.CreateTimerScreen.route)
                }
            )
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
            val id = entry.arguments?.getString("id") ?: ""
            val name = entry.arguments?.getString("name") ?: ""
            val secondsNumber = entry.arguments?.getInt("seconds") ?: 0
            val timer = Timer(id, name, secondsNumber)

            TimerScreen(
                timer = timer,
                onTimerStopped = {
                    viewModel.clearTimers()
                    navController.popBackStack()
                },
                onTimerFinished = {
                    if (timers.isEmpty()) {
                        navController.popBackStack(Screen.MainScreen.route, inclusive = false)
                    } else {
                        viewModel.popTimer()?.let { timer ->
                            val route = Screen.TimerScreen.routeWithArgs(
                                timer.id.toString(),
                                timer.name.toString(),
                                timer.secondsCount.toString()
                            )
                            navController.navigate(route = route) {
                                popUpTo(Screen.MainScreen.route)
                            }
                        } ?: run {
                            navController.popBackStack()
                        }
                    }
                }
            )
        }

        composable(route = Screen.CreateTimerScreen.route) {
            CreateTimerScreen {
                navController.popBackStack()
            }
        }
    }
}