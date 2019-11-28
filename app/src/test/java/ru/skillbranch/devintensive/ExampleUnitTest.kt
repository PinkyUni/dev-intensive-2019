package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
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

}
