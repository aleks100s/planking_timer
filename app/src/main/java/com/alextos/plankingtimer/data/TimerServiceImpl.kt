package com.alextos.plankingtimer.data

import com.alextos.plankingtimer.domain.services.TimerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TimerServiceImpl(private val timerScope: CoroutineScope): TimerService {
    private var _timerStateFlow = MutableStateFlow(0)
    val timerStateFlow: StateFlow<Int> = _timerStateFlow

    private var job: Job? = null

    init {
        startTimer(60)
    }

    override fun startTimer(time: Int) {
        job = if (job == null) {
            timerScope.launch {
                (time - 1 downTo 0).asFlow()
                    .onEach { delay(1000) }
                    .onStart { emit(time) }
                    .conflate()
                    .onCompletion { _timerStateFlow.emit(0) }
                    .collect { _timerStateFlow.emit(it) }
            }
        } else {
            job?.cancel()
            null
        }
    }

    override fun stopTimer() {
        job?.cancel()
    }
}