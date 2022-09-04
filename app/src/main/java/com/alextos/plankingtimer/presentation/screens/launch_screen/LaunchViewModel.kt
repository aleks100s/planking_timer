package com.alextos.plankingtimer.presentation.screens.launch_screen

import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.domain.services.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val authenticationService: AuthenticationService
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