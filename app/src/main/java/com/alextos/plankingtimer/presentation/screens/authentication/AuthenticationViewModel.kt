package com.alextos.plankingtimer.presentation.screens.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.data.AuthenticationServiceImpl
import com.alextos.plankingtimer.domain.services.AuthenticationService

class AuthenticationViewModel(
    private val authenticationService: AuthenticationService = AuthenticationServiceImpl()
): ViewModel() {

    data class State(
        val isLogin: Boolean,
        val email: String,
        val password: String
    )

    var state = mutableStateOf(
        State(
            isLogin = true,
            email = "",
            password = ""
        )
    )
        private set

    fun changeAuthenticationMode(isLogin: Boolean) {
        state.value = state.value.copy(isLogin = isLogin)
    }

    fun onEmailChanged(email: String) {
        state.value = state.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        state.value = state.value.copy(password = password)
    }

    fun authenticate() {
        if (state.value.isLogin) {
            authenticationService.login(state.value.email, state.value.password)
        } else {
            authenticationService.signUp(state.value.email, state.value.password)
        }
    }
}