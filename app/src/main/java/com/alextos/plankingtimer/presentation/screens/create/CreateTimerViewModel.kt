package com.alextos.plankingtimer.presentation.screens.create

import androidx.compose.runtime.State
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

    data class CreateTimerState(
        val title: UiText = UiText.StringResource(R.string.timer_title),
        val parts: List<TimerPart> = listOf(TimerPart(
            title = UiText.StringResource(R.string.timer),
            secondsCount = STEP)
        )
    )

    companion object {
        const val STEP = 15
    }

    private val _state = mutableStateOf(CreateTimerState())
    val state: State<CreateTimerState> = _state

    fun saveTimer(completion: () -> Unit) {
        completion()
    }

    fun timerTitleChanged(title: String) {
        _state.value = _state.value.copy(title = UiText.DynamicString(title))
    }

    fun addNewPart() {
        val currentParts = _state.value.parts.toMutableList()
        val newPart = TimerPart(title = UiText.StringResource(R.string.timer), secondsCount = STEP)
        currentParts.add(newPart)
        _state.value = _state.value.copy(parts = currentParts)
    }

    fun timerPartTitleChanged(index: Int, title: String) {
        val currentParts = _state.value.parts
        currentParts[index].title = UiText.DynamicString(title)
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