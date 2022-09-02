package com.alextos.plankingtimer.domain.model.creation

import com.alextos.plankingtimer.domain.util.UiText

data class TimerPart(
    var title: UiText,
    var secondsCount: Int
)
