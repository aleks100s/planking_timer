package com.alextos.plankingtimer.domain.services

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface TimerService {
    val timerStateFlow: StateFlow<Int>

    fun startTimer(time: Int, scope: CoroutineScope)
    fun stopTimer()
}
