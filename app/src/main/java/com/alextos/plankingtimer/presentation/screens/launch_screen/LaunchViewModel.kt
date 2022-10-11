package com.alextos.plankingtimer.presentation.screens.launch_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class LaunchViewModel: ViewModel() {

    fun loadApplication(
        completion: () -> Unit
    ) {
        completion()
    }
}