package com.alextos.plankingtimer.common.util

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

class SoundPlayer(private val context: Context) {

    fun playSound(@RawRes soundId: Int) {
        val mediaPlayer = MediaPlayer.create(context, soundId)
        mediaPlayer.start()
    }
}