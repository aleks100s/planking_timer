package com.alextos.plankingtimer.presentation.screens.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alextos.plankingtimer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(onAuthenticationSuccess: () -> Unit) {
    val viewModel: AuthenticationViewModel = viewModel()
    val state = viewModel.state.value

    val title = if (state.isLogin)
        stringResource(id = R.string.login)
    else
        stringResource(id = R.string.sign_up)

    val changeModeText = if (state.isLogin)
        stringResource(id = R.string.sign_up)
    else
        stringResource(id = R.string.login)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
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
                    viewModel.changeAuthenticationMode(!state.isLogin)
                }
        )
    }
}