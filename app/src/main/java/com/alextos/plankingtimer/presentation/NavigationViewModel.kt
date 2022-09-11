package com.alextos.plankingtimer.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.domain.model.main.Timer

class NavigationViewModel: ViewModel() {
    private val _state = mutableStateOf<List<Timer>>(listOf())
    val state: State<List<Timer>> = _state

    fun popTimer(): Timer? {
        return _state.value.toMutableList().removeFirstOrNull()
    }

    fun clearTimers() {
        _state.value.toMutableList().clear()
    }

    fun addTimers(list: List<Timer>) {
        _state.value = list
    }
}