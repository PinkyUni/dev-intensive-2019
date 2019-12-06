package ru.skillbranch.devintensive

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
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

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener {
            processAnswer()
        }

        messageEt.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                processAnswer()
                handled = true
            }
            handled
        }
    }

    private fun processAnswer() {
        val (text, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase(Locale.ROOT))
        textTxt.text = text
        benderImage.setColorFilter(
            Color.rgb(color.first, color.second, color.third),
            PorterDuff.Mode.MULTIPLY
        )
        messageEt.text.clear()
        this.hideKeyboard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS", benderObj.status.name)
        outState.putString("QUESTION", benderObj.question.name)
    }
}
