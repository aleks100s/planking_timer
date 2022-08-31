package com.alextos.plankingtimer.presentation.screens.timer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alextos.plankingtimer.R

@Composable
fun TimerScreen(
    timerName: String,
    secondsNumber: Int,
    onTimerStopped: () -> Unit,
    onTimerFinished: () -> Unit
) {
    val viewModel = viewModel<TimerViewModel>()
    val state = viewModel.timerState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.startTimer(secondsNumber, completion = onTimerFinished)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = timerName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(32.dp)
            ) {
                CircularProgressIndicator(
                    progress = 1f,
                    strokeWidth = 8.dp,
                    color = Color.Gray,
                    modifier = Modifier.size(300.dp)
                )

                CircularProgressIndicator(
                    progress = state.value / secondsNumber.toFloat(),
                    strokeWidth = 8.dp,
                    modifier = Modifier.size(300.dp)
                )

                Text("${state.value}")
            }

            Button(onClick = {
                viewModel.stopTimer()
                onTimerStopped()
            }) {
                Text(stringResource(id = R.string.stop))
            }
        }
    }
}