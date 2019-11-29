package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?>? {
        val name = fullName?.trim()
        if (name == null || name.isEmpty()) {
            return Pair(null, null)
        }
        val parts = name.split(" ")
        return Pair(parts.getOrNull(0), parts.getOrNull(1))
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val f = firstName?.trim()?.getOrNull(0)?.toUpperCase()
        val l = lastName?.trim()?.getOrNull(0)?.toUpperCase()
        return if (f != null && l != null) "$f$l" else if (f != null) "$f" else if (l != null) "$l" else null
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val string = payload.replace(" ", divider)
        val map = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )
        return buildString {
            for (char in string) {
                val res = when {
                    char.isUpperCase() -> map[char.toLowerCase().toString()]?.toUpperCase(Locale.ENGLISH)
                    else -> map[char.toString()]
                }
                if (res != null)
                    append(res)
                else
                    append(char)
            }
        }
    }

}