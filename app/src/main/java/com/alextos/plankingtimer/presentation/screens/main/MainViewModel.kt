package com.alextos.plankingtimer.presentation.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.domain.model.TimerQueue

class MainViewModel: ViewModel() {

    data class MainState(val timers: List<TimerQueue>)

    var state = mutableStateOf(MainState(timers = listOf(TimerQueue.timer, TimerQueue.timer, TimerQueue.timer)))
        private set
}