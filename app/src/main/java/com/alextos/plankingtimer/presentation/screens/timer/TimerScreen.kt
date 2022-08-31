package com.alextos.plankingtimer.presentation.screens.timer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alextos.plankingtimer.R

@Composable
fun TimerScreen() {
    val viewModel = viewModel<TimerViewModel>()
    val state = viewModel.timerStateFlow.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.startTimer()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    state.value / 60f,
                    Modifier.clickable {  }
                )
                Text("${state.value}")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.stopTimer()
            }) {
                Text(stringResource(id = R.string.stop))
            }
        }
    }
}