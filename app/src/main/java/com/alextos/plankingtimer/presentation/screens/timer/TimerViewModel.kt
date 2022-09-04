package com.alextos.plankingtimer.presentation.screens.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.domain.services.TimerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerService: TimerService
): ViewModel() {

    val timerState = timerService.timerStateFlow

    fun startTimer(secondsNumber: Int, completion: () -> Unit) {
        timerService.startTimer(
            time = secondsNumber,
            scope = viewModelScope
        )

        viewModelScope.launch {
            timerState.collect {
                if (it == 0) {
                    completion()
                }
            }
        }
    }

    fun stopTimer() {
        timerService.stopTimer()
    }
}