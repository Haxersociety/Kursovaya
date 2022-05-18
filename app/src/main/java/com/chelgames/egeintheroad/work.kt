package com.chelgames.egeintheroad

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chelgames.egeintheroad.db.MyDBManager
import com.google.android.material.textfield.TextInputEditText
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class work : AppCompatActivity() {

    private val myDBManager = MyDBManager(this)
    private var numbers = ArrayList<Int>()
    private var answers = ArrayList<String>()
    private var trueAnswers = ArrayList<Int>()
    private var exSize = ArrayList<Int>()
    private var page = 0
    private var move = 0
    private var tvPages:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
    }

    // Загрузка страницы
    private fun getWeb(file: String) {
        val tvWeb: WebView = findViewById(R.id.test_web)
        tvWeb.webViewClient = WebViewClient()
        tvWeb.canGoBack()
        tvWeb.apply {
            settings.allowFileAccess = true
            loadUrl("file:///data/data/com.chelgames.egeintheroad/files/$file")
            settings.javaScriptEnabled = true
        }
    }

    private fun addInstruction(){
    }

    // Переход к следующему заданию
    fun nextClick(view: View){

    }

    // Обработка нажатия на кнопку "Назад"
    override fun onBackPressed(){
    }
}



