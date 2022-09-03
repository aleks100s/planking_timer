package com.alextos.plankingtimer.presentation.screens.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.data.AuthenticationServiceImpl
import com.alextos.plankingtimer.data.RepositoryServiceImpl
import com.alextos.plankingtimer.domain.model.creation.TimerPart
import com.alextos.plankingtimer.domain.model.main.Timer
import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.services.AuthenticationService
import com.alextos.plankingtimer.domain.services.RepositoryService
import java.util.*

class CreateTimerViewModel(
    private val repositoryService: RepositoryService = RepositoryServiceImpl(),
    private val authenticationService: AuthenticationService = AuthenticationServiceImpl()
): ViewModel() {

    data class CreateTimerState(
        val title: String = "",
        val parts: List<TimerPart> = listOf(TimerPart(
            title = "",
            secondsCount = STEP)
        )
    )

    companion object {
        const val STEP = 15
    }

    private val _state = mutableStateOf(CreateTimerState())
    val state: State<CreateTimerState> = _state

    fun saveTimer(completion: () -> Unit) {
        val timerQueue = TimerQueue(
            id = UUID.randomUUID().toString(),
            title = _state.value.title,
            timers = _state.value.parts.map {
                Timer(
                    id = UUID.randomUUID().toString(),
                    name = it.title,
                    secondsCount = it.secondsCount
                )
            }
        )
        repositoryService.saveTimer(
            timer = timerQueue,
            collection = authenticationService.getUserId() ?: ""
        )
        completion()
    }

    fun timerTitleChanged(title: String) {
        _state.value = _state.value.copy(title = title)
    }

    fun addNewPart() {
        val currentParts = _state.value.parts.toMutableList()
        val newPart = TimerPart(title = "", secondsCount = STEP)
        currentParts.add(newPart)
        _state.value = _state.value.copy(parts = currentParts)
    }

    fun timerPartTitleChanged(index: Int, title: String) {
        val currentParts = _state.value.parts
        currentParts[index].title = title
        _state.value = _state.value.copy(parts = listOf())
        _state.value = _state.value.copy(parts = currentParts)
    }

    fun increaseTimerPart(index: Int) {
        val currentParts = _state.value.parts
        currentParts[index].secondsCount += STEP
        _state.value = _state.value.copy(parts = listOf())
        _state.value = _state.value.copy(parts = currentParts)
    }

    fun decreaseTimerPart(index: Int) {
        val currentParts = state.value.parts
        currentParts[index].secondsCount -= STEP
        _state.value = _state.value.copy(parts = listOf())
        _state.value = _state.value.copy(parts = currentParts)
    }
}