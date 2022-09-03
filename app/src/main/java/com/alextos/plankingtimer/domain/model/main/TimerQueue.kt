package com.alextos.plankingtimer.domain.model.main

import java.util.*

data class TimerQueue(
    val id: String? = null,
    val title: String? = null,
    val timers: List<Timer>? = null
) {
    fun totalSeconds(): Int {
        return timers?.map {
            it.secondsCount ?: 0
        }?.reduce { acc, secondsCount ->
            acc + secondsCount
        } ?: 0
    }

    companion object {
        val timer = TimerQueue(
            id = UUID.randomUUID().toString(),
            title = "My planking timer",
            timers = listOf(Timer.dummyTimer1)
        )

        val timer2 = TimerQueue(
            id = UUID.randomUUID().toString(),
            title = "My planking timer 2",
            timers = listOf(Timer.dummyTimer1, Timer.dummyTimer2)
        )
    }
}