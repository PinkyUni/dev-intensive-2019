package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val formatter = SimpleDateFormat(pattern, Locale("ru"))
    return formatter.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    return Date(this.time + value * units.value * 1000)
}

fun Date.humanizeDiff(): String {
    return when (val diff = (Date().time - this.time) / 1000) {
        in 0..1 -> "только что"
        in 1..45 -> "несколько секунд назад"
        in 45..75 -> "минуту назад"
        in 75..45 * TimeUnits.MINUTE.value -> "${diff / TimeUnits.MINUTE.value} минут${when (diff / TimeUnits.MINUTE.value) {
            1.toLong() -> "у"
            in 2..4 -> "ы"
            else -> ""
        }} назад"
        in 45 * TimeUnits.MINUTE.value..75 * TimeUnits.MINUTE.value -> "час назад"
        in 75 * TimeUnits.MINUTE.value..22 * TimeUnits.HOUR.value -> "${diff / TimeUnits.HOUR.value} час${when (diff / TimeUnits.HOUR.value) {
            in 2..4 -> "а"
            in 5..9 -> "ов"
            else -> ""
        }} назад"
        in 22 * TimeUnits.HOUR.value..26 * TimeUnits.HOUR.value -> "день назад"
        in 26 * TimeUnits.HOUR.value..360 * TimeUnits.DAY.value -> "${diff / TimeUnits.DAY.value} ${when (diff / TimeUnits.DAY.value) {
            1.toLong() -> "день"
            in 2..4 -> "дня"
            else -> "дней"
        }} назад"
        else -> "более года назад"
    }
}

enum class TimeUnits(val value: Long) {
    SECOND(1), MINUTE(60), HOUR(60 * 60), DAY(24 * 60 * 60)
}