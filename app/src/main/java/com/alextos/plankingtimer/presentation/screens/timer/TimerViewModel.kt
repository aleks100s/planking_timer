package com.alextos.plankingtimer.presentation.screens.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.data.TimerServiceImpl
import com.alextos.plankingtimer.domain.model.Timer
import com.alextos.plankingtimer.domain.services.TimerService
import kotlinx.coroutines.flow.*

class TimerViewModel: ViewModel() {
    private val timerService: TimerService = TimerServiceImpl()
    val timerStateFlow: StateFlow<Int> = timerService.timerStateFlow

    fun startTimer() {
        timerService.startTimer(
            timer = Timer.dummyTimer,
            scope = viewModelScope
        )
    }

    fun stopTimer() {
        timerService.stopTimer()
    }
}