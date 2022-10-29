package com.alextos.plankingtimer.presentation.screens.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.alextos.plankingtimer.R
import com.alextos.plankingtimer.presentation.theme.TimerBarColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TimersCounter(count: Int) {
    val counter = if (count > 5) 5 else count
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = pluralStringResource(
                id = R.plurals.remaining_timers,
                count = count,
                count
            )
        )

        LazyRow(horizontalArrangement = Arrangement.End) {
            items(counter) { index ->
                TimerThumbnail(alpha = 1F - index * 0.2F)
            }
        }
    }
}

@Composable
private fun TimerThumbnail(alpha: Float) {
    Canvas(
        modifier = Modifier
            .alpha(alpha)
            .size(24.dp)
            .padding(4.dp)
    ) {
        drawCircle(
            color = TimerBarColor,
            radius = 10.dp.toPx(),
            style = Stroke(width = 2.dp.toPx())
        )
    }
}