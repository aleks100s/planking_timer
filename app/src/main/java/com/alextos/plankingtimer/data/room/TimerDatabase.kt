package com.alextos.plankingtimer.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alextos.plankingtimer.data.model.TimerEntity
import com.alextos.plankingtimer.data.model.TimerQueueEntity

@Database(
    entities = [TimerEntity::class, TimerQueueEntity::class],
    version = 1
)
abstract class TimerDatabase : RoomDatabase() {
    abstract fun timerDao(): TimerDao
}