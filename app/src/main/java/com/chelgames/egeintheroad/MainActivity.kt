package com.chelgames.egeintheroad

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_main)

        getWeb("file:///android_asset/page")
    }

    fun clickOnStart(view: View){
        val intent = Intent(this, exercises::class.java)
        startActivity(intent)
    }

    fun clickOnStat(view: View){
        val intent2 = Intent(this, statistic::class.java)
        startActivity(intent2)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun getWeb(file: String) {
        val tvWeb: WebView = findViewById(R.id.test_web)
        tvWeb.webViewClient = WebViewClient()
        tvWeb.canGoBack()
        tvWeb.apply {
            settings.allowFileAccess = true
            loadUrl(file)
            settings.javaScriptEnabled = true
        }
    }

    private fun addInstruction(){
        val File_name = "lol.html"
        val text = "Ответ сохраняется только после нажатия на кнопку \"Принять\""
        val fos = openFileOutput(File_name, Context.MODE_PRIVATE)
        val outputWriter = OutputStreamWriter(fos)
        outputWriter.write(text)
        outputWriter.close()
    }

    //TODO Переделать БД.

    //TODO Переделать интерфейс.

    //TODO Провести рефактор кода, оставить только необходимые функции (Сомневаюсь что вообще что-либо останется).

    //TODO Распарсить сайт Яндекса на задачи
    // https://yandex.ru/tutor/subject/tag/problems/?ege_number_id=283&tag_id=19
    // Версия для печати:
    // https://yandex.ru/tutor/subject/tag/problems/?ege_number_id=283&print=1&tag_id=19
    // Парсить по Task-Description, это готовое задание для вставки в webView,
    // только прежде чем туда вставлять нужно немного подредактировать эту html'ку.
    // Например убрать строку "Показать полностью".
}
