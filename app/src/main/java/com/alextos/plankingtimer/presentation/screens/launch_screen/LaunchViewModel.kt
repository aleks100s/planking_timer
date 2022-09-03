package com.alextos.plankingtimer.presentation.screens.launch_screen

import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.data.AuthenticationServiceImpl
import com.alextos.plankingtimer.domain.services.AuthenticationService

class LaunchViewModel(
    private val authenticationService: AuthenticationService = AuthenticationServiceImpl()
): ViewModel() {

    fun checkAuthentication(
        success: () -> Unit,
        failure: () -> Unit
    ) {
        if (authenticationService.isUserAuthenticated()) {
            success()
        } else {
            failure()
        }
    }
}