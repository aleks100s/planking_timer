package com.alextos.plankingtimer.common.util

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import com.alextos.plankingtimer.R

class SoundPlayer(private val context: Context) {

    fun playSound(@RawRes soundId: Int) {
        val mediaPlayer = MediaPlayer.create(context, soundId)
        mediaPlayer.start()
    }
}