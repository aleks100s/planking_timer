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
import androidx.hilt.navigation.compose.hiltViewModel
import com.alextos.plankingtimer.R

@Composable
fun LaunchScreen(
    onAuthenticationNeeded: () -> Unit,
    onLoadingFinished: () -> Unit
) {
    val viewModel: LaunchViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        viewModel.checkAuthentication(
            success = onLoadingFinished,
            failure = onAuthenticationNeeded
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