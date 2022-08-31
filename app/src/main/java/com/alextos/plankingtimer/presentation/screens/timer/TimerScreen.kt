package com.alextos.plankingtimer.presentation.screens.timer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TimerScreen(time: Int) {
    val viewModel = viewModel<TimerViewModel>()
    val state = viewModel.timerStateFlow.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            state.value / 60f,
            Modifier.clickable {  }
        )
        Text("${state.value}")
    }
}