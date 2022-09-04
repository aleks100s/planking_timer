package com.alextos.plankingtimer.presentation.screens.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alextos.plankingtimer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(onAuthenticationSuccess: (String) -> Unit) {
    val viewModel: AuthenticationViewModel = hiltViewModel()
    val state = viewModel.state.value

    LaunchedEffect(key1 = state.userId) {
        state.userId?.let(onAuthenticationSuccess)
    }

    val title = when (state.mode) {
        is AuthenticationViewModel.AuthMode.Login -> stringResource(id = R.string.login)
        is AuthenticationViewModel.AuthMode.SignUp -> stringResource(id = R.string.sign_up)
    }

    val changeModeText = when (state.mode) {
        is AuthenticationViewModel.AuthMode.SignUp -> stringResource(id = R.string.login)
        is AuthenticationViewModel.AuthMode.Login -> stringResource(id = R.string.sign_up)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = snackbarHostState) {
        viewModel.errors.collect {
            snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
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

            Text(
                changeModeText,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        when (state.mode) {
                            is AuthenticationViewModel.AuthMode.Login -> {
                                viewModel.changeAuthenticationMode(AuthenticationViewModel.AuthMode.SignUp)
                            }
                            is AuthenticationViewModel.AuthMode.SignUp -> {
                                viewModel.changeAuthenticationMode(AuthenticationViewModel.AuthMode.Login)
                            }
                        }
                    }
            )
        }
    }
}