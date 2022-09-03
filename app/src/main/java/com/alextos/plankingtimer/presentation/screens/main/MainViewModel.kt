package com.alextos.plankingtimer.presentation.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.data.RepositoryServiceImpl
import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.services.RepositoryService
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: RepositoryService = RepositoryServiceImpl()
): ViewModel() {

    data class MainState(val timers: List<TimerQueue> = listOf())

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    fun subscribe(collection: String) {
        viewModelScope.launch {
            repository.subscribeTimerList(collection).collect { list ->
                _state.value = _state.value.copy(timers = list)
            }
        }
    }
}