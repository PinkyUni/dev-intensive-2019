package ru.skillbranch.devintensive.models

import java.util.*

data class User(
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
) {

    companion object Factory {

        var user_id: Int = 0

        fun makeUser(fullName: String): User {
            user_id++
            val parts = fullName.split(" ")
            val firstName = parts[0]
            val lastName = parts[1]
            return User(user_id.toString(), firstName, lastName, null)
        }

    }

}