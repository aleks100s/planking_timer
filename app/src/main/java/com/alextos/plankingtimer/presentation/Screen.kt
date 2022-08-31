package com.alextos.plankingtimer.presentation

sealed class Screen(val route: String) {
    object LaunchScreen: Screen("launch_screen")
    object MainScreen: Screen("main_screen")
    object TimerScreen: Screen("timer_screen")

    fun routeWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { append("/$it") }
        }
    }
}
