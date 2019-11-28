package ru.skillbranch.devintensive.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val formatter = SimpleDateFormat(pattern, Locale("ru"))
    return formatter.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    return Date(this.time + value * units.value*1000)
}

enum class TimeUnits(val value: Long) {
    SECOND(1), MINUTE(60), HOUR(60 * 60), DAY(24 * 60 * 60)
}