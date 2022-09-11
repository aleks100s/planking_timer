package com.alextos.plankingtimer.presentation.screens.timer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.R
import com.alextos.plankingtimer.common.services.TimerNotificationService
import com.alextos.plankingtimer.common.util.SoundPlayer
import com.alextos.plankingtimer.domain.services.TimerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerService: TimerService,
    private val soundPlayer: SoundPlayer,
    private val notificationService: TimerNotificationService
): ViewModel() {

    data class TimerState(
        val hasFinished: Boolean
    )

    private val _state = mutableStateOf(TimerState(hasFinished = false))
    val state: State<TimerState> = _state

    val timerState = timerService.timerStateFlow

    fun startTimer(secondsNumber: Int) {
        timerService.startTimer(
            time = secondsNumber,
            scope = viewModelScope
        )

        viewModelScope.launch {
            timerState.collect {
                notificationService.showNotification(it)
                if (it == 0) {
                    notificationService.removeNotification()
                    soundPlayer.playSound(R.raw.timer_finish)
                    _state.value = TimerState(hasFinished = true)
                }
            }
        }
    }

    fun stopTimer() {
        notificationService.removeNotification()
        timerService.stopTimer()
    }
}