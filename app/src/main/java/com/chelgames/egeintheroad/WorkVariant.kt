package com.chelgames.egeintheroad

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.chelgames.egeintheroad.db.MyDBManager
import com.google.android.material.textfield.TextInputEditText


class WorkVariant : AppCompatActivity() {

    private val myDBManager = MyDBManager(this)
    private var exercises = ArrayList<Int>()
    private var answers = ArrayList<String>()
    private var trueAnswers = ArrayList<String>()
    private var currentPage = 1
    private var textViewPages: TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        textViewPages = findViewById(R.id.tvPages)

        val myAssetManager = applicationContext.assets

        val categories = myAssetManager.list("exercises")
        //Создаем массив с заданиями
        for (i in 0 until categories?.size!!) {
            val exercisesList = myAssetManager.list("exercises/rus_${i + 1}")
            val randomEx = (0 until exercisesList?.size!!).random()
            exercises.add(randomEx)
            answers.add("")

            val trueAnswer: String =
                applicationContext.assets
                    .open("answers/rus_${i + 1}/$randomEx.html")
                    .bufferedReader().use {
                        it.readText()
                    }
            println(trueAnswer)
            trueAnswers.add(trueAnswer)
        }
        getWeb("rus_1/${exercises[0]}.html")
        textViewPages?.setText("$currentPage/25")

    }

    // Загрузка страницы
    @SuppressLint("SetJavaScriptEnabled")
    private fun getWeb(file: String) {
        val tvWeb: WebView = findViewById(R.id.test_web)
        tvWeb.webViewClient = WebViewClient()
        tvWeb.canGoBack()
        tvWeb.apply {
            settings.allowFileAccess = true
            loadUrl("file:///android_asset/exercises/${file}")
            settings.javaScriptEnabled = true
        }
    }

    // Переход к следующему заданию
    @SuppressLint("SetTextI18n")
    fun nextClick(view: View){
        if(currentPage < 25) {
            currentPage++
            getWeb("rus_${currentPage}/${exercises[currentPage - 1]}.html")
            textViewPages?.setText("$currentPage/25")

            //Запись ответа в массив
            val tvText = findViewById<TextInputEditText>(R.id.tvText)
            val text = tvText.text.toString()
            answers[currentPage-2] = text
            tvText?.setText(answers[currentPage-1])

        } else {
            val myDialogFragment = MyDialogFragment()
            val manager = supportFragmentManager
            myDialogFragment.show(manager, "myDialog")
        }
    }

    @SuppressLint("SetTextI18n")
    fun prevClick(view: View){
        if(currentPage > 1) {
            currentPage--
            getWeb("rus_${currentPage}/${exercises[currentPage - 1]}.html")
            textViewPages?.setText("$currentPage/25")

            //Запись ответа в массив
            val tvText = findViewById<TextInputEditText>(R.id.tvText)
            val text = tvText.text.toString()
            answers[currentPage] = text
            tvText?.setText(answers[currentPage-1])
        }
    }

    fun finishTry(){
        val intent = Intent(this, Result::class.java).apply{
            putExtra("trueAnswers", trueAnswers)
            putExtra("answers", answers)
        }
        startActivity(intent)
    }

    // Обработка нажатия на кнопку "Назад"
    override fun onBackPressed(){
    }
}



