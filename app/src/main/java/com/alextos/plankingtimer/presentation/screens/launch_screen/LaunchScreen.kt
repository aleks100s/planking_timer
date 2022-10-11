package com.alextos.plankingtimer.presentation.screens.launch_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alextos.plankingtimer.R

@Composable
fun LaunchScreen(
    onLoadingFinished: () -> Unit
) {
    val viewModel: LaunchViewModel = viewModel()

    LaunchedEffect(key1 = true) {
        viewModel.loadApplication(
            completion = onLoadingFinished
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(stringResource(id = R.string.app_name))
    }
}