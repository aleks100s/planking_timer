package com.alextos.plankingtimer.common.services

interface TimerNotificationService {
    fun showNotification(time: Int)
    fun removeNotification()
}