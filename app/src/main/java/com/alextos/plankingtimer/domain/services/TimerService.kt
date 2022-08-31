package com.alextos.plankingtimer.domain.services

import com.alextos.plankingtimer.domain.model.Timer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface TimerService {
    val timerStateFlow: StateFlow<Int>

    fun startTimer(timer: Timer, scope: CoroutineScope)
    fun stopTimer()
}
