package com.alextos.plankingtimer.presentation.screens.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.data.TimerServiceImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {
    private val timerService = TimerServiceImpl(viewModelScope)
    val timerStateFlow: StateFlow<Int> = timerService.timerStateFlow

    init {
        viewModelScope.launch {
            timerStateFlow.collectLatest {
                Log.e("qweqw", "$it")
            }
        }
    }
}