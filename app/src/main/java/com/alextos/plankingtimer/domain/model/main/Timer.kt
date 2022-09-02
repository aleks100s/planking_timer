package com.alextos.plankingtimer.domain.model.main

import java.util.*

data class Timer(
    val id: UUID,
    var name: String,
    var secondsCount: Int
) {
    companion object {
        val dummyTimer1 = Timer(
            id = UUID.randomUUID(),
            name = "Timer 1",
            secondsCount = 10
        )

        val dummyTimer2 = Timer(
            id = UUID.randomUUID(),
            name = "Timer 2",
            secondsCount = 15
        )
    }
}
