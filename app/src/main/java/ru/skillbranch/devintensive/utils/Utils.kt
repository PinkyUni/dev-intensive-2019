package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?>? {
        val name = fullName?.trim()
        if (name == null || name.isEmpty()) {
            return Pair(null, null)
        }
        val parts = name.split(" ")
        return Pair(parts.getOrNull(0), parts.getOrNull(1))
    }

}