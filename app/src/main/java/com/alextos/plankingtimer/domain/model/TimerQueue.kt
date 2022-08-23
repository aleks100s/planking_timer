package com.alextos.plankingtimer.domain.model

import java.util.*

data class TimerQueue(
    val id: UUID,
    val title: String,
    val timers: List<Timer>
) {
}