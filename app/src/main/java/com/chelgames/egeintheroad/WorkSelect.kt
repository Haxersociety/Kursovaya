package com.chelgames.egeintheroad

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.chelgames.egeintheroad.db.MyDBManager
import com.chelgames.egeintheroad.db.MyDBManager2
import com.google.android.material.textfield.TextInputEditText


class WorkSelect : AppCompatActivity() {

    private val myDBManager2 = MyDBManager2(this)
    private var currentEx = 1
    private var currentNumber = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_select)

        myDBManager2.openDB()
        val data = myDBManager2.readDBData()
        if (data.size > 0) {
            val id = data[0].substringBefore('/').toInt()
            val ex = data[0].substringAfter('/').toInt()
            myDBManager2.deleteFromDB(id)

            val exercisesList = applicationContext.assets.list("exercises/rus_${ex}")
            val randomEx = (0 until exercisesList?.size!!).random()

            currentEx = ex
            currentNumber = randomEx

            getWeb("rus_${ex}/${randomEx}.html")
        } else {

            val categories = applicationContext.assets.list("exercises")
            val randomEx = (0 until categories?.size!!).random()

            val exercisesList = applicationContext.assets.list("exercises/rus_${randomEx}")
            val randomNumber = (0 until exercisesList?.size!!).random()


            getWeb("rus_${randomEx}/${randomNumber}.html")
        }

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
    fun nextClick(view: View) {
        val trueAnswer: String =
            applicationContext.assets
                .open("answers/rus_$currentEx/$currentNumber.html")
                .bufferedReader().use {
                    it.readText()
                }
        val tvText = findViewById<TextInputEditText>(R.id.tvText)
        val answer = tvText.text.toString()

        if (trueAnswer != answer.toLowerCase().replace(" ", ""))
            myDBManager2.insertToDB(currentEx)

        //Генерация следующей страницы
        val data = myDBManager2.readDBData()
        if (data.size > 0) {
            val id = data[0].substringBefore('/').toInt()
            val ex = data[0].substringAfter('/').toInt()
            myDBManager2.deleteFromDB(id)

            val exercisesList = applicationContext.assets.list("exercises/rus_${ex}")
            val randomEx = (0 until exercisesList?.size!!).random()

            currentEx = ex
            currentNumber = randomEx

            getWeb("rus_${ex}/${randomEx}.html")

        } else {

            val categories = applicationContext.assets.list("exercises")
            val randomEx = (0 until categories?.size!!).random()

            val exercisesList = applicationContext.assets.list("exercises/rus_${randomEx}")
            val randomNumber = (0 until exercisesList?.size!!).random()

            getWeb("rus_${randomEx}/${randomNumber}.html")

        }

    }

    fun prevClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        myDBManager2.insertToDB(currentEx)
    }

    // Обработка нажатия на кнопку "Назад"
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        myDBManager2.insertToDB(currentEx)
    }
}



