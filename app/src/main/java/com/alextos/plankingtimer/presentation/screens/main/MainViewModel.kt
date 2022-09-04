package com.alextos.plankingtimer.presentation.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.services.AuthenticationService
import com.alextos.plankingtimer.domain.services.RepositoryService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RepositoryService,
    private val authenticationService: AuthenticationService
): ViewModel() {

    data class MainState(val timers: List<TimerQueue> = listOf())

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        viewModelScope.launch {
            val userId = authenticationService.getUserId() ?: ""
            repository.subscribeTimerList(userId).collect { list ->
                _state.value = _state.value.copy(timers = list)
            }
        }
    }

    fun deleteTimer(timer: TimerQueue) {
        repository.deleteTimer(
            timer = timer,
            collection = authenticationService.getUserId() ?: ""
        )
    }
}