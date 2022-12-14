package com.alextos.plankingtimer.domain.services

import com.alextos.plankingtimer.domain.model.main.TimerQueue
import kotlinx.coroutines.flow.Flow

interface RepositoryService {
    fun saveTimer(timer: TimerQueue)
    fun deleteTimer(timer: TimerQueue)
    fun subscribeTimerList(): Flow<List<TimerQueue>>
}