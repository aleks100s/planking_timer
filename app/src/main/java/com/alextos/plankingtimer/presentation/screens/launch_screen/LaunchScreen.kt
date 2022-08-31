package com.alextos.plankingtimer.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alextos.plankingtimer.R
import com.alextos.plankingtimer.presentation.Screen
import com.alextos.plankingtimer.presentation.screens.launch_screen.LaunchViewModel

@Composable
fun LaunchScreen(navController: NavController) {
    val viewModel = viewModel<LaunchViewModel>()
    val state = viewModel.state

    LaunchedEffect(key1 = state.value.isLoaded) {
        if (state.value.isLoaded) {
            navController.navigate(route = Screen.TimerScreen.route)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(stringResource(id = R.string.app_name))
    }
}