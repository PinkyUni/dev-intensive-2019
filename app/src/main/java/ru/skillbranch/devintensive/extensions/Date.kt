package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val formatter = SimpleDateFormat(pattern, Locale("ru"))
    return formatter.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    return Date(this.time + value * units.value)
}

fun Date.humanizeDiff(): String {
    val diff = (this.time - Date().time)
    return when (abs(diff)) {
        in 0..1 * TimeUnits.SECOND.value -> "только что"
        in 1 * TimeUnits.SECOND.value..45 * TimeUnits.SECOND.value -> if (diff > 0) "через несколько секунд" else "несколько секунд назад"
        in 45 * TimeUnits.SECOND.value..75 * TimeUnits.SECOND.value -> if (diff > 0) "через минуту" else "минуту назад"
        in 75 * TimeUnits.SECOND.value..45 * TimeUnits.MINUTE.value -> when (abs(diff / TimeUnits.MINUTE.value)) {
            1L -> if (diff > 0) "через минуту" else "минуту назад"
            in 2..4 -> if (diff > 0) "через ${diff / TimeUnits.MINUTE.value} минуты" else "${abs(
                diff / TimeUnits.MINUTE.value
            )} минуты назад"
            else -> if (diff > 0) "через ${diff / TimeUnits.MINUTE.value} минут" else "${abs(diff / TimeUnits.MINUTE.value)} минут назад"
        }
        in 45 * TimeUnits.MINUTE.value..75 * TimeUnits.MINUTE.value -> "час назад"
        in 75 * TimeUnits.MINUTE.value..22 * TimeUnits.HOUR.value -> when (abs(diff / TimeUnits.HOUR.value)) {
            1L -> if (diff > 0) "через час" else "час назад"
            in 2..4 -> if (diff > 0) "через ${abs(diff / TimeUnits.HOUR.value)} часа" else "${abs(
                diff / TimeUnits.HOUR.value
            )} часа назад"
            else -> if (diff > 0) "через ${abs(diff / TimeUnits.HOUR.value)} часов" else "${abs(diff / TimeUnits.HOUR.value)} часов назад"
        }
        in 22 * TimeUnits.HOUR.value..26 * TimeUnits.HOUR.value -> "день назад"
        in 26 * TimeUnits.HOUR.value..360 * TimeUnits.DAY.value -> when (abs(diff / TimeUnits.DAY.value)) {
            1L -> if (diff > 0) "через день" else "день назад"
            in 2..4 -> if (diff > 0) "через ${abs(diff / TimeUnits.DAY.value)} дня" else "${abs(diff / TimeUnits.DAY.value)} дня назад"
            else -> if (diff > 0) "через ${abs(diff / TimeUnits.DAY.value)} дней" else "${abs(diff / TimeUnits.DAY.value)} дней назад"
        }
        else -> if (diff / TimeUnits.DAY.value > 0) "более чем через год" else "более года назад"
    }
}

enum class TimeUnits(val value: Long) {
    SECOND(1000L), MINUTE(60 * SECOND.value), HOUR(60 * MINUTE.value), DAY(24 * HOUR.value);

    fun plural(value: Int): String {
        return "$value ${when (this) {
            SECOND -> when (value % 10) {
                1 -> "секунду"
                in 2..4 -> "секунды"
                else -> "секунд"
            }
            MINUTE -> when (value % 10) {
                1 -> "минуту"
                in 2..4 -> "минуты"
                else -> "минут"
            }
            HOUR -> when (value % 10) {
                1 -> "час"
                in 2..4 -> "часа"
                else -> "часов"
            }
            DAY -> when (value % 10) {
                1 -> "день"
                in 2..4 -> "дня"
                else -> "дней"
            }
        }}"
    }
}