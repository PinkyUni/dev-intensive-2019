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
        val user = User.makeUser("Василий Кузнецов")
        val chat = Chat("1")
        val date = Date()
        println(
            BaseMessage.makeMessage(
                user,
                chat,
                date,
                "any text message",
                "text"
            ).formatMessage()
        ) //Василий отправил сообщение "any text message" только что
        println(
            BaseMessage.makeMessage(
                user,
                chat,
                date.add(-2, TimeUnits.HOUR),
                "https://anyurl.com",
                "image",
                true
            ).formatMessage()
        ) //Василий получил изображение "https://anyurl.com" 2 часа назад

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

        println(Utils.toInitials("john", "doe")) //JD
        println(Utils.toInitials("John", null)) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null
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

    @Test
    fun test_plural() {
        println(TimeUnits.SECOND.plural(1)) //1 секунду
        println(TimeUnits.MINUTE.plural(4)) //4 минуты
        println(TimeUnits.HOUR.plural(19)) //19 часов
        println(TimeUnits.DAY.plural(222)) //222 дня
    }

}
