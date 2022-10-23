package com.alextos.plankingtimer.common.util

fun Int.timeString(): String {
    val minutes = if (this / 60 < 10)
        "0${this / 60}"
    else
        "${this / 60}"

    val seconds = if (this % 60 < 10)
        "0${this % 60}"
    else
        "${this % 60}"

    return "$minutes:$seconds"
}