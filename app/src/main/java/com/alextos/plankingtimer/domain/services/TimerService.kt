package com.alextos.plankingtimer.domain.services

import com.alextos.plankingtimer.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface TimerService {
    fun startTimer(time: Int): Flow<Resource<Int>>
    fun stopTimer()
}
