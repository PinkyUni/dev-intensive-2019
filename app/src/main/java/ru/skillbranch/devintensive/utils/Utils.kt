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

    fun toInitials(firstName: String?, lastName: String?): String {
        val f = firstName?.trim()?.getOrNull(0)
        val l = lastName?.trim()?.getOrNull(0)
        return if (f != null && l != null) "$f$l" else "$f"
    }

}