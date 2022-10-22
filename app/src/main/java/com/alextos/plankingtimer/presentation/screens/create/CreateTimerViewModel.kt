package com.alextos.plankingtimer.presentation.screens.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextos.plankingtimer.domain.model.creation.TimerPart
import com.alextos.plankingtimer.domain.model.main.Timer
import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.services.RepositoryService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateTimerViewModel @Inject constructor(
    private val repositoryService: RepositoryService
): ViewModel() {

    data class CreateTimerState(
        val title: String = "",
        val parts: List<TimerPart> = listOf()
    ) {
        fun isValid(): Boolean {
            return title.isNotBlank()
                    && parts.isNotEmpty()
                    && parts.all { it.title.isNotBlank() }
        }
    }

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

        viewModelScope.launch(Dispatchers.IO) {
            repositoryService.saveTimer(timer = timerQueue)
        }

        completion()
    }

    fun timerTitleChanged(title: String) {
        _state.value = _state.value.copy(title = title)
    }

    fun addNewPart(title: String) {
        val currentParts = _state.value.parts.toMutableList()
        val newPart = TimerPart(title = "$title ${getTimerStepNumber()}", secondsCount = STEP)
        currentParts.add(newPart)
        _state.value = _state.value.copy(parts = currentParts)
    }

    private fun getTimerStepNumber(): Int {
        return _state.value.parts.count() + 1
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

    fun deletePart(index: Int) {
        val currentParts = _state.value.parts.toMutableList()
        currentParts.removeAt(index)
        _state.value = _state.value.copy(parts = currentParts)
    }
}