package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    val string = this.trim()
    return if (string.length > value)
        "${string.substring(0 until value).trim()}..."
    else
        string
}