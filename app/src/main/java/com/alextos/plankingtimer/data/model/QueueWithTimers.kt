package com.alextos.plankingtimer.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.alextos.plankingtimer.domain.model.main.Timer
import com.alextos.plankingtimer.domain.model.main.TimerQueue

data class QueueWithTimers(
    @Embedded
    val queueEntity: TimerQueueEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "queueId"
    )
    val timers: List<TimerEntity>
) {
    fun toTimerQueue(): TimerQueue {
        return TimerQueue(
            id = queueEntity.id,
            title = queueEntity.title,
            timers = timers.map {
                Timer(
                    id = it.id,
                    name = it.name,
                    secondsCount = it.secondsCount
                )
            }
        )
    }
}