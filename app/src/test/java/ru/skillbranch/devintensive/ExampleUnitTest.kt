package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extension.TimeUnits
import ru.skillbranch.devintensive.extension.add
import ru.skillbranch.devintensive.extension.format
import ru.skillbranch.devintensive.extension.humanizeDiff
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_user_factory() {
        val user = User.makeUser("Pinky Uni")
        println(user)
    }

    @Test
    fun test_message_factory() {
        val user = User.makeUser("Pinky Uni")

        val m1 = BaseMessage.makeMessage(user, Chat(), Date(), "image", "image message")
        val m2 = BaseMessage.makeMessage(user, Chat(), Date(), "t", "text message", isIncoming = true)

        println(m1.formatMessage())
        println(m2.formatMessage())

    }

    @Test
    fun test_parse_fullname() {
        var pair = Utils.parseFullName("Anya kek")
        println(pair)
        pair = Utils.parseFullName("Anya ")
        println(pair)
        pair = Utils.parseFullName(" ")
        println(pair)
        pair = Utils.parseFullName("")
        println(pair)
        pair = Utils.parseFullName(null)
        println(pair)
    }

    @Test
    fun test_date_utils() {
        println(Date().format("HH:mm"))
    }

    @Test
    fun test_date_add() {
        println(Date().add(-2, TimeUnits.HOUR))
    }

    @Test
    fun test_initials() {
        println(Utils.toInitials("Anya", "Kek"))
        println(Utils.toInitials("Anya", ""))
        println(Utils.toInitials("Anya", " "))
        println(Utils.toInitials(" ", ""))
        println(Utils.toInitials(" ", "Lol"))
        println(Utils.toInitials("Kek", null))
        println(Utils.toInitials(null, null))
    }

    @Test
    fun test_transliteration() {
        println(Utils.transliteration("Аня Кек", " "))
        println(Utils.transliteration("Anya Кек", "+"))
    }

    @Test
    fun test_humanize_date() {
        val date = Date().add(-4, TimeUnits.DAY)
        println(date)
        println(date.humanizeDiff())
    }

}
