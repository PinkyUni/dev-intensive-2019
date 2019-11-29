package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val formatter = SimpleDateFormat(pattern, Locale("ru"))
    return formatter.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    return Date(this.time + value * units.value * 1000)
}

fun Date.humanizeDiff(): String {
    val diff = (this.time - Date().time) / 1000
    return when (abs(diff)) {
        in 0..1 -> "только что"
        in 1..45 -> if (diff > 0) "через несколько секунд" else "несколько секунд назад"
        in 45..75 -> if (diff > 0) "через минуту" else "минуту назад"
        in 75..45 * TimeUnits.MINUTE.value -> when (abs(diff / TimeUnits.MINUTE.value)) {
            1.toLong() -> if (diff > 0) "через минуту" else "минуту назад"
            in 2..4 -> if (diff > 0) "через ${diff / TimeUnits.MINUTE.value} минуты" else "${abs(diff / TimeUnits.MINUTE.value)} минуты назад"
            else -> if (diff > 0) "через ${diff / TimeUnits.MINUTE.value} минут" else "${abs(diff / TimeUnits.MINUTE.value)} минут назад"
        }
        in 45 * TimeUnits.MINUTE.value..75 * TimeUnits.MINUTE.value -> "час назад"
        in 75 * TimeUnits.MINUTE.value..22 * TimeUnits.HOUR.value -> when (abs(diff / TimeUnits.HOUR.value)) {
            1.toLong() -> if (diff  > 0) "через час" else "час назад"
            in 2..4 -> if (diff > 0) "через ${abs(diff / TimeUnits.HOUR.value)} часа" else "${abs(diff / TimeUnits.HOUR.value)} часа назад"
            else -> if (diff > 0) "через ${abs(diff / TimeUnits.HOUR.value)} часов" else "${abs(diff / TimeUnits.HOUR.value)} часов назад"
        }
        in 22 * TimeUnits.HOUR.value..26 * TimeUnits.HOUR.value -> "день назад"
        in 26 * TimeUnits.HOUR.value..360 * TimeUnits.DAY.value -> when (abs(diff / TimeUnits.DAY.value)) {
            1.toLong() -> if (diff > 0) "через день" else "день назад"
            in 2..4 -> if (diff > 0) "через ${abs(diff / TimeUnits.DAY.value)} дня" else "${abs(diff / TimeUnits.DAY.value)} дня назад"
            else -> if (diff > 0) "через ${abs(diff / TimeUnits.DAY.value)} дней" else "${abs(diff / TimeUnits.DAY.value)} дней назад"
        }
        else -> if (diff / TimeUnits.DAY.value > 0) "более чем через год" else "более года назад"
    }
}

enum class TimeUnits(val value: Long) {
    SECOND(1), MINUTE(60), HOUR(60 * 60), DAY(24 * 60 * 60)
}