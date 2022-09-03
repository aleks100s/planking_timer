package com.alextos.plankingtimer.presentation.screens.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.data.AuthenticationServiceImpl
import com.alextos.plankingtimer.domain.services.AuthenticationService
import com.alextos.plankingtimer.domain.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val authenticationService: AuthenticationService = AuthenticationServiceImpl()
): ViewModel() {

    sealed class AuthMode {
        object Login: AuthMode()
        object SignUp: AuthMode()
    }

    data class AuthenticationState(
        val mode: AuthMode = AuthMode.Login,
        val email: String = "",
        val password: String = "",
        val userId: String? = null
    )

    private val _state = mutableStateOf(AuthenticationState())
    val state: State<AuthenticationState> = _state

    private val errorChannel = Channel<String>()
    val errors = errorChannel.receiveAsFlow()

    fun changeAuthenticationMode(mode: AuthMode) {
        _state.value = _state.value.copy(mode = mode)
    }

    fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun authenticate() {
        when(_state.value.mode) {
            is AuthMode.Login -> {
                authenticationService.login(
                    _state.value.email,
                    _state.value.password,
                    completion = this::authenticationHandler
                )
            }

            is AuthMode.SignUp -> {
                authenticationService.signUp(
                    _state.value.email,
                    _state.value.password,
                    completion = this::authenticationHandler
                )
            }
        }
    }

    private fun authenticationHandler(result: Result<String>) {
        when (result) {
            is Result.Success -> {
                _state.value = _state.value.copy(userId = result.data)
            }
            is Result.Error -> {
                viewModelScope.launch {
                    errorChannel.send(result.message ?: "")
                }
            }
        }
    }
}