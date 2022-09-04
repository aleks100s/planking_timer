package com.alextos.plankingtimer

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.alextos.plankingtimer.common.services.TimerNotificationServiceImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlankingTimerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            TimerNotificationServiceImpl.CHANNEL_ID,
            getString(R.string.timer_channel),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = getString(R.string.timer_channel_description)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}