package com.alextos.plankingtimer.presentation.screens.timer

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alextos.plankingtimer.R
import com.alextos.plankingtimer.domain.model.main.Timer

data class TimerData(
    val title: String,
    val currentTime: Int,
    val totalTime: Int,
    val isLandscape: Boolean
)

@Composable
fun TimerScreen(
    timer: Timer,
    onTimerStopped: () -> Unit,
    onTimerFinished: () -> Unit
) {
    val viewModel = viewModel<TimerViewModel>()
    val state = viewModel.timerState.collectAsState()

    val timerHasStarted = rememberSaveable {
        mutableStateOf(false)
    }
    if (!timerHasStarted.value) {
        LaunchedEffect(key1 = true) {
            viewModel.startTimer(timer.secondsCount, onTimerFinished)
            timerHasStarted.value = true
        }
    }

    val isLandscape = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            true
        }
        else -> {
            false
        }
    }

    val timerData = TimerData(
        title = timer.name,
        currentTime = state.value,
        totalTime = timer.secondsCount,
        isLandscape = isLandscape
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isLandscape) {
            HorizontalTimer(data = timerData) {
                viewModel.stopTimer()
                onTimerStopped()
            }
        } else {
            VerticalTimer(data = timerData) {
                viewModel.stopTimer()
                onTimerStopped()
            }
        }
    }
}

@Composable
fun HorizontalTimer(data: TimerData, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimerClock(
            currentTime = data.currentTime,
            totalTime = data.totalTime,
            isLandscape = data.isLandscape
        )

        Spacer(modifier = Modifier.width(32.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TimerTitle(title = data.title)

            Spacer(modifier = Modifier.height(32.dp))

            StopButton(onClick)
        }
    }
}

@Composable
fun VerticalTimer(data: TimerData, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TimerTitle(title = data.title)

        TimerClock(
            currentTime = data.currentTime,
            totalTime = data.totalTime,
            isLandscape = data.isLandscape
        )

        StopButton(onClick)
    }
}

@Composable
fun TimerTitle(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun TimerClock(currentTime: Int, totalTime: Int, isLandscape: Boolean) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(16.dp)
            .aspectRatio(1f)
    ) {
        TimerCircle(
            color = Color.Gray,
            isHeightFirst = isLandscape
        )

        TimerCircle(
            color = Color.Green,
            isHeightFirst = isLandscape,
            progress = currentTime / totalTime.toFloat()
        )

        Text("$currentTime", fontSize = 24.sp)
    }
}

@Composable
fun TimerCircle(color: Color, isHeightFirst: Boolean, progress: Float = 1f) {
    CircularProgressIndicator(
        progress = progress,
        strokeWidth = 8.dp,
        color = color,
        modifier = Modifier
            .aspectRatio(1f, isHeightFirst)
            .apply {
                if (isHeightFirst) {
                    fillMaxHeight()
                } else {
                    fillMaxWidth()
                }
            }
    )
}

@Composable
fun StopButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(id = R.string.stop))
    }
}