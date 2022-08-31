package com.alextos.plankingtimer.presentation.screens.launch_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LaunchViewModel: ViewModel() {

    data class LaunchState(val isLoaded: Boolean)

    var state = mutableStateOf(LaunchState(isLoaded = false))
        private set

    init {
        viewModelScope.launch {
            (0..1).asFlow()
                .onEach { delay(100) }
                .onEach { print(it) }
                .onCompletion { state.value = LaunchState(isLoaded = true) }
                .collect()
        }
    }
}