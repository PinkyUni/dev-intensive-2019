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
            status = status.nextStatus()
            "Это неправильный ответ\n${question.question}" to status.color
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