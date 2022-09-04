package com.alextos.plankingtimer.domain.model.creation

import com.alextos.plankingtimer.R
import com.alextos.plankingtimer.common.util.UiText

data class TimerPart(
    var title: String,
    var secondsCount: Int
) {
    fun timeString(): UiText {
        return UiText.StringResource(
            R.string.time,
            secondsCount / 60,
            secondsCount % 60
        )
    }
}
