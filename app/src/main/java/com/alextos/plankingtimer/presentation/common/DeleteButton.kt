package com.alextos.plankingtimer.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alextos.plankingtimer.R

@Composable
fun DeleteButton(onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Filled.Delete,
        contentDescription = stringResource(id = R.string.delete_timer),
        tint = Color.Red,
        modifier = Modifier
            .alpha(0.5f)
            .clickable { onClick() }
            .padding(8.dp)
    )
}