//package ru.skillbranch.devintensive.models
//
//import java.util.*
//
//class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {
//
//    fun askQuestion() = when (question) {
//        Question.NAME -> Question.NAME.question
//        Question.PROFESSION -> Question.PROFESSION.question
//        Question.MATERIAL -> Question.MATERIAL.question
//        Question.BDAY -> Question.BDAY.question
//        Question.SERIAL -> Question.SERIAL.question
//        Question.IDLE -> Question.IDLE.question
//    }
//
//    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> =
//        when (question) {
//            Question.NAME ->
//                if (!answer[0].isUpperCase())
//                    "Имя должно начинаться с заглавной буквы\n" + question.question to status.color
//                else
//                    process(answer)
//            Question.PROFESSION ->
//                if (!answer[0].isLowerCase())
//                    "Профессия должна начинаться со строчной буквы\n" + question.question to status.color
//                else
//                    process(answer)
//            Question.MATERIAL ->
//                if (answer.contains(Regex("[0-9]+")))
//                    "Материал не должен содержать цифр\n" + question.question to status.color
//                else
//                    process(answer)
//            Question.BDAY ->
//                if (answer.contains(Regex("[^0-9]")))
//                    "Год моего рождения должен содержать только цифры\n" + question.question to status.color
//                else
//                    process(answer)
//            Question.SERIAL ->
//                if (answer.length == 7 && answer.contains(Regex("\\d{7}")))
//                    process(answer)
//                else
//                    "Серийный номер содержит только цифры, и их 7\n" + question.question to status.color
//            Question.IDLE ->
//                process(answer)
//        }
//
//    private fun process(answer: String): Pair<String, Triple<Int, Int, Int>> =
//        if (question.answers.contains(answer.toLowerCase(Locale.ROOT))) {
//            if (question == Question.IDLE)
//                "Отлично - ты справился\nНа этом все, вопросов больше нет" to status.color
//            else {
//                question = question.nextQuestion()
//                "Отлично - ты справился\n${question.question}" to status.color
//            }
//        } else {
//            if (status == Status.CRITICAL) {
//                status = Status.NORMAL
//                question = Question.NAME
//                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
//            } else {
//                status = status.nextStatus()
//                "Это неправильный ответ\n${question.question}" to status.color
//            }
//        }
//
//    enum class Status(val color: Triple<Int, Int, Int>) {
//        NORMAL(Triple(255, 255, 255)),
//        WARNING(Triple(255, 120, 0)),
//        DANGER(Triple(255, 60, 60)),
//        CRITICAL(Triple(255, 0, 0));
//
//        fun nextStatus(): Status =
//            if (this.ordinal < values().lastIndex) {
//                values()[this.ordinal + 1]
//            } else {
//                values()[0]
//            }
//
//    }
//
//    enum class Question(val question: String, val answers: List<String>) {
//        NAME("Как меня зовут?", listOf("Бендер", "bender")),
//        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")),
//        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")),
//        BDAY("Когда меня создали?", listOf("2993")),
//        SERIAL("Мой серийный номер?", listOf("2716057")),
//        IDLE("На этом все, вопросов больше нет", listOf());
//
//        fun nextQuestion(): Question =
//            if (this.ordinal < values().lastIndex) {
//                values()[this.ordinal + 1]
//            } else {
//                IDLE
//            }
//
//    }
//
//
//}

package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion() = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> =
        if (question.answers.contains(answer)) {
            if (question == Question.IDLE)
                "Отлично - ты справился\nНа этом все, вопросов больше нет" to status.color
            else {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            }
        } else {
            if (status == Status.CRITICAL) {
                status = Status.NORMAL
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status =
            if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }

    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "bender")),
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")),
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")),
        BDAY("Когда меня создали?", listOf("2993")),
        SERIAL("Мой серийный номер?", listOf("2716057")),
        IDLE("На этом все, вопросов больше нет", listOf());

        fun nextQuestion(): Question =
            if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                IDLE
            }

    }


}