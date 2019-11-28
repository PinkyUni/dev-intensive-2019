package ru.skillbranch.devintensive.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val formatter =SimpleDateFormat(pattern, Locale("ru"))
    return formatter.format(this)
}
