package com.alextos.plankingtimer.presentation.screens.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alextos.plankingtimer.R
import com.alextos.plankingtimer.data.RepositoryServiceImpl
import com.alextos.plankingtimer.domain.model.creation.TimerPart
import com.alextos.plankingtimer.domain.services.RepositoryService
import com.alextos.plankingtimer.domain.util.UiText

class CreateTimerViewModel(
    private val repositoryService: RepositoryService = RepositoryServiceImpl()
): ViewModel() {

    data class State(
        val title: UiText,
        val parts: List<TimerPart>
    )

    companion object {
        const val STEP = 15
    }

    val state = mutableStateOf(State(
        title = UiText.StringResource(R.string.timer_title),
        parts = listOf(TimerPart(title = UiText.StringResource(R.string.timer), secondsCount = STEP))
    ))

    fun saveTimer(completion: () -> Unit) {
        completion()
    }

    fun timerTitleChanged(title: String) {
        state.value = state.value.copy(title = UiText.DynamicString(title))
    }

    fun addNewPart() {
        val currentParts = state.value.parts.toMutableList()
        val newPart = TimerPart(title = UiText.StringResource(R.string.timer), secondsCount = STEP)
        currentParts.add(newPart)
        state.value = state.value.copy(parts = currentParts)
    }

    fun timerPartTitleChanged(index: Int, title: String) {
        val currentParts = state.value.parts
        currentParts[index].title = UiText.DynamicString(title)
        state.value = state.value.copy(parts = listOf())
        state.value = state.value.copy(parts = currentParts)
    }

    fun increaseTimerPart(index: Int) {
        val currentParts = state.value.parts
        currentParts[index].secondsCount += STEP
        state.value = state.value.copy(parts = listOf())
        state.value = state.value.copy(parts = currentParts)
    }

    fun decreaseTimerPart(index: Int) {
        val currentParts = state.value.parts
        currentParts[index].secondsCount -= STEP
        state.value = state.value.copy(parts = listOf())
        state.value = state.value.copy(parts = currentParts)
    }
}