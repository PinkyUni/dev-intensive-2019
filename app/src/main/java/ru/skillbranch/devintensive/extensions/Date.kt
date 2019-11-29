package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val formatter = SimpleDateFormat(pattern, Locale("ru"))
    return formatter.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    return Date(this.time + value * units.value * SECOND)
}

fun Date.humanizeDiff(): String {
    val diff = (this.time - Date().time)
    return when (abs(diff)) {
        in 0..1 -> "только что"
        in 1..45 -> if (diff > 0) "через несколько секунд" else "несколько секунд назад"
        in 45..75 -> if (diff > 0) "через минуту" else "минуту назад"
        in 75..45 * MINUTE -> when (abs(diff / MINUTE)) {
            1L -> if (diff > 0) "через минуту" else "минуту назад"
            in 2..4 -> if (diff > 0) "через ${diff / MINUTE} минуты" else "${abs(
                diff / MINUTE
            )} минуты назад"
            else -> if (diff > 0) "через ${diff / MINUTE} минут" else "${abs(diff / MINUTE)} минут назад"
        }
        in 45 * MINUTE..75 * MINUTE -> "час назад"
        in 75 * MINUTE..22 * HOUR -> when (abs(diff / HOUR)) {
            1L -> if (diff > 0) "через час" else "час назад"
            in 2..4 -> if (diff > 0) "через ${abs(diff / HOUR)} часа" else "${abs(
                diff / HOUR
            )} часа назад"
            else -> if (diff > 0) "через ${abs(diff / HOUR)} часов" else "${abs(diff / HOUR)} часов назад"
        }
        in 22 * HOUR..26 * HOUR -> "день назад"
        in 26 * HOUR..360 * DAY -> when (abs(diff / DAY)) {
            1L -> if (diff > 0) "через день" else "день назад"
            in 2..4 -> if (diff > 0) "через ${abs(diff / DAY)} дня" else "${abs(diff / DAY)} дня назад"
            else -> if (diff > 0) "через ${abs(diff / DAY)} дней" else "${abs(diff / DAY)} дней назад"
        }
        else -> if (diff / TimeUnits.DAY.value > 0) "более чем через год" else "более года назад"
    }
}

enum class TimeUnits(val value: Long) {
    SECOND(1), MINUTE(60), HOUR(60 * 60), DAY(24 * 60 * 60);

    fun plural(value: Int): String {
        println(this.name)
        return value.toString()
    }
}