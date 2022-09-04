package com.alextos.plankingtimer.common.services

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.alextos.plankingtimer.R
import javax.inject.Inject

class TimerNotificationServiceImpl @Inject constructor(
    private val context: Context
): TimerNotificationService {

    companion object {
        const val CHANNEL_ID = "timer_channel"
        const val NOTIFICATION_ID = 1
    }

    private val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(time: Int) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_timer_24)
            .setContentTitle(context.getString(R.string.remaining_time))
            .setContentText(context.getString(R.string.time, time / 60, time % 60))
            .setOngoing(true)
            .setTimeoutAfter(2000)
            .build()

        manager.notify(NOTIFICATION_ID, notification)
    }

    override fun removeNotification() {
        manager.cancel(NOTIFICATION_ID)
    }
}