package com.uniandes.wakey

fun formatNumberTime(number: Int) = if (number > 9) {
    number.toString()
} else {
    "0$number"
}

fun formatTime(hour: Int, minute: Int): String {
    val isAM = hour < 12
    val formattedHour = if (hour == 0) 12 else if (hour > 12) hour - 12 else hour
    val formattedMinute = minute.toString().padStart(2, '0')
    val amPm = if (isAM) "AM" else "PM"
    return "$formattedHour:$formattedMinute $amPm"
}