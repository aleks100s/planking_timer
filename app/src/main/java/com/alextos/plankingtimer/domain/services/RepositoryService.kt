package com.alextos.plankingtimer.domain.services

import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface RepositoryService {
    fun saveTimer(timer: TimerQueue, collection: String)
    fun deleteTimer(timer: TimerQueue, collection: String)
    fun subscribeTimerList(collection: String): Flow<List<TimerQueue>>
}