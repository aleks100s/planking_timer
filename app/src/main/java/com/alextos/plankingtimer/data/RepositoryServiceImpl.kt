package com.alextos.plankingtimer.data

import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.services.RepositoryService
import com.alextos.plankingtimer.domain.util.Resource

class RepositoryServiceImpl: RepositoryService {
    override suspend fun getTimerList(collection: String): Resource<List<TimerQueue>> {
        TODO("Not yet implemented")
    }

    override fun saveTimer(timer: TimerQueue, collection: String) {
        TODO("Not yet implemented")
    }

    override fun deleteTimer(timer: TimerQueue, collection: String) {
        TODO("Not yet implemented")
    }
}