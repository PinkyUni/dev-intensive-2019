package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.humanizeDiff
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

        val m1 = BaseMessage.makeMessage(user, Chat("1"), Date(), "image", "image message")
        val m2 = BaseMessage.makeMessage(user, Chat("2"), Date(), "t", "text message", isIncoming = true)

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
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год
    }

    @Test
    fun test_user_builder() {
        val s = "s"
        val n = 3
        val d = Date()
        val b = true
        val user = User.Builder().id(s)
            .firstName(s)
            .lastName(s)
            .avatar(s)
            .rating(n)
            .respect(n)
            .lastVisit(d)
            .isOnline(b)
            .build()
        print(user)
    }

}
