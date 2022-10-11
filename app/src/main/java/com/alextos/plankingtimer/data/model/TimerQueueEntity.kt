package com.alextos.plankingtimer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queue")
data class TimerQueueEntity(
    @PrimaryKey
    val id: String,
    val title: String,
)
