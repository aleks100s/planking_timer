package com.alextos.plankingtimer.data

import com.alextos.plankingtimer.domain.model.Timer
import com.alextos.plankingtimer.domain.services.TimerService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class TimerServiceImpl: TimerService {
    private var _timerStateFlow = MutableStateFlow(0)
    override val timerStateFlow: StateFlow<Int> = _timerStateFlow

    private var currentTimer: Job? = null

    override fun startTimer(timer: Timer, scope: CoroutineScope) {
        currentTimer?.cancel()
        val time = timer.secondsCount

        currentTimer = scope.launch {
            (time - 1 downTo 0).asFlow()
                .onEach { delay(1000) }
                .onStart { emit(time) }
                .conflate()
                .onCompletion { _timerStateFlow.emit(0) }
                .collect { _timerStateFlow.emit(it) }
        }
    }

    override fun stopTimer() {
        currentTimer?.cancel()
    }
}