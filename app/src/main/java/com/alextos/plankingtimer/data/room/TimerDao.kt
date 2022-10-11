package com.alextos.plankingtimer.data.room

import androidx.room.*
import com.alextos.plankingtimer.data.model.QueueWithTimers
import com.alextos.plankingtimer.data.model.TimerEntity
import com.alextos.plankingtimer.data.model.TimerQueueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerDao {
    @Transaction
    @Query("SELECT * from queue;")
    fun getAll(): Flow<List<QueueWithTimers>>

    @Insert
    fun insertQueue(queue: TimerQueueEntity)

    @Insert
    fun insertTimers(timers: List<TimerEntity>)

    @Delete
    fun deleteTimers(timers: List<TimerEntity>)

    @Delete
    fun deleteQueue(queue: TimerQueueEntity)
}