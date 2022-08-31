package com.alextos.plankingtimer.presentation.screens.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.data.TimerServiceImpl
import com.alextos.plankingtimer.domain.services.TimerService

class TimerViewModel(
    private val timerService: TimerService = TimerServiceImpl()
): ViewModel() {

    val timerState = timerService.timerStateFlow

    fun startTimer(secondsNumber: Int, completion: () -> Unit) {
        timerService.startTimer(
            time = secondsNumber,
            scope = viewModelScope,
            completion = completion
        )
    }

    fun stopTimer() {
        timerService.stopTimer()
    }
}