package com.alextos.plankingtimer.domain.services

import kotlinx.coroutines.flow.StateFlow

interface TimerService {
    fun startTimer(time: Int)
    fun stopTimer()
}
