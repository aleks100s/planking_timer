package com.alextos.plankingtimer.presentation.screens.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alextos.plankingtimer.R

@Composable
fun AuthenticationScreen(onAuthenticationSuccess: () -> Unit) {
    val viewModel: AuthenticationViewModel = viewModel()
    val state = viewModel.state.value

    val title = if (state.isLogin)
        stringResource(id = R.string.login)
    else
        stringResource(id = R.string.sign_up)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = title,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = state.email,
                onValueChange = viewModel::onEmailChanged,
                label = {
                    Text(text = stringResource(id = R.string.enter_email))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = state.password,
                onValueChange = viewModel::onPasswordChanged,
                label = {
                    Text(text = stringResource(id = R.string.enter_password))
                },
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = viewModel::authenticate) {
                Text(title)
            }
        }
    }
}