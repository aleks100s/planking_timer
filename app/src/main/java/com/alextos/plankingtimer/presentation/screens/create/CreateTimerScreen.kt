package com.alextos.plankingtimer.presentation.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alextos.plankingtimer.R

@Composable
fun CreateTimerScreen(onTimerCreated: () -> Unit) {

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.new_timer),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}