package com.alextos.plankingtimer.presentation.screens.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.data.RepositoryServiceImpl
import com.alextos.plankingtimer.domain.model.creation.TimerPart
import com.alextos.plankingtimer.domain.services.RepositoryService

class CreateTimerViewModel(
    private val repositoryService: RepositoryService = RepositoryServiceImpl()
): ViewModel() {

    data class State(
        val title: String,
        val parts: List<TimerPart>
    )

    val state = mutableStateOf(State(
        title = "Timer Title",
        parts = listOf(TimerPart(title = "Timer", secondsCount = 15))
    ))

    fun saveTimer(completion: () -> Unit) {
        completion()
    }

    fun timerTitleChanged(title: String) {
        state.value = state.value.copy(title = title)
    }

    fun addNewPart() {
        val currentParts = state.value.parts.toMutableList()
        val newPart = TimerPart(title = "Timer", secondsCount = 15)
        currentParts.add(newPart)
        state.value = state.value.copy(parts = currentParts)
    }

    fun timerPartTitleChanged(index: Int, title: String) {
        val currentParts = state.value.parts
        currentParts[index].title = title
        state.value = state.value.copy(parts = currentParts)
    }

    fun increaseTimerPart(index: Int) {
        val currentParts = state.value.parts
        currentParts[index].secondsCount += 15
        state.value = state.value.copy(parts = currentParts)
    }

    fun decreaseTimerPart(index: Int) {
        val currentParts = state.value.parts
        currentParts[index].secondsCount -= 15
        state.value = state.value.copy(parts = currentParts)
    }
}