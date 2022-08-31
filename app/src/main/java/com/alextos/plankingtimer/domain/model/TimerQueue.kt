package com.alextos.plankingtimer.domain.model

import java.util.*

data class TimerQueue(
    val id: UUID,
    val title: String,
    val timers: List<Timer>
) {
    fun totalSeconds(): Int {
        return timers
            .map { it.secondsCount }
            .reduce { acc, secondsCount ->
                acc + secondsCount
        }
    }

    companion object {
        val timer = TimerQueue(
            id = UUID.randomUUID(),
            title = "My planking timer",
            timers = listOf(Timer.dummyTimer)
        )
    }
}