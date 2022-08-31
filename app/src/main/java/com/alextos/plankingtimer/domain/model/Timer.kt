package com.alextos.plankingtimer.domain.model

import java.util.*

data class Timer(
    val id: UUID,
    var name: String,
    var secondsCount: Int
) {
    companion object {
        val dummyTimer = Timer(
            id = UUID.randomUUID(),
            name = "Timer",
            secondsCount = 60
        )
    }
}
