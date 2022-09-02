package com.alextos.plankingtimer.domain.services

import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.util.Resource

interface RepositoryService {
    suspend fun getTimerList(collection: String): Resource<List<TimerQueue>>
    fun saveTimer(timer: TimerQueue, collection: String)
    fun deleteTimer(timer: TimerQueue, collection: String)
    //fun subscribeTimerList(collection: String): Flow<List<TimerQueue>>
}