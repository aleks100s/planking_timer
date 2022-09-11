package com.alextos.plankingtimer.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.domain.model.main.Timer

class NavigationViewModel: ViewModel() {
    private val _state = mutableStateOf<List<Timer>>(listOf())
    val state: State<List<Timer>> = _state

    fun popTimer(): Timer? {
        val list = _state.value.toMutableList()
        val timer =  list.removeFirstOrNull()
        _state.value = list
        return timer
    }

    fun clearTimers() {
        _state.value = listOf()
    }

    fun addTimers(list: List<Timer>) {
        _state.value = list
    }
}