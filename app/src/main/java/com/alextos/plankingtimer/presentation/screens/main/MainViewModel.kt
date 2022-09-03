package com.alextos.plankingtimer.presentation.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.domain.model.main.TimerQueue

class MainViewModel: ViewModel() {

    data class MainState(val timers: List<TimerQueue>)

    private val _state = mutableStateOf(MainState(
        timers = listOf(TimerQueue.timer, TimerQueue.timer2)
    ))
    val state: State<MainState> = _state
}