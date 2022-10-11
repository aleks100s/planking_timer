package com.alextos.plankingtimer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timer")
data class TimerEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val secondsCount: Int,
    val queueId: String
)