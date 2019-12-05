package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        benderObj = Bender()
        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        benderObj.status = Bender.Status.valueOf(status)
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj.question = Bender.Question.valueOf(question)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener {
            val (text, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase(Locale.ROOT))
            textTxt.text = text
            benderImage.setColorFilter(
                Color.rgb(color.first, color.second, color.third),
                PorterDuff.Mode.MULTIPLY
            )
            messageEt.text.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS", benderObj.status.name)
        outState.putString("QUESTION", benderObj.question.name)
    }
}
