package com.alextos.plankingtimer.data

import android.content.Context
import androidx.room.Room
import com.alextos.plankingtimer.data.model.TimerEntity
import com.alextos.plankingtimer.data.model.TimerQueueEntity
import com.alextos.plankingtimer.data.room.TimerDatabase
import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.services.RepositoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryServiceImpl @Inject constructor(context: Context): RepositoryService {
    private val database: TimerDatabase

    init {
        database = Room.databaseBuilder(
            context,
            TimerDatabase::class.java, "timer_queue_database"
        ).build()
    }

    override fun subscribeTimerList(): Flow<List<TimerQueue>> {
        return database.timerDao().getAll()
            .map { list ->
                list.map { it.toTimerQueue() }
            }
    }

    override fun saveTimer(timer: TimerQueue) {
        val queue = TimerQueueEntity(id = timer.id, title = timer.title)
        database.timerDao().insertQueue(queue)

        val timers = timer.timers.map {
            TimerEntity(
                id = it.id,
                name = it.name,
                secondsCount = it.secondsCount,
                queueId = queue.id
            )
        }
        database.timerDao().insertTimers(timers)
    }

    override fun deleteTimer(timer: TimerQueue) {
        val queue = TimerQueueEntity(id = timer.id, title = timer.title)
        database.timerDao().deleteQueue(queue)

        val timers = timer.timers.map {
            TimerEntity(
                id = it.id,
                name = it.name,
                secondsCount = it.secondsCount,
                queueId = queue.id
            )
        }
        database.timerDao().deleteTimers(timers)
    }
}