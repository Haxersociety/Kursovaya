package com.chelgames.egeintheroad

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chelgames.egeintheroad.db.myDBManager
import com.google.android.material.textfield.TextInputEditText
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class work : AppCompatActivity() {
    private val myDBManager = myDBManager(this)
    private var numbers = ArrayList<Int>()
    private var answers = ArrayList<String>()
    private var trueAnswers = ArrayList<Int>()
    private var exSize = ArrayList<Int>()
    private var Page = 0
    private var move = 0
    private var tvPages:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
        myDBManager.openDB()
        tvPages = findViewById(R.id.tvPages)
        val data = myDBManager.readDBDataBySubj("rus")
        exSize.add(0)
        for(i in 0 until data.size){
            exSize.add(0)
            val dt = data[i]
            val ex = dt.substringBefore("+")
            val sz = dt.substringAfter("+")
            exSize[ex.toInt()] += sz.toInt()
        }
        addInstruction()
        numbers.add(0)
        answers.add("0")
        for(i in 0..26){
            trueAnswers.add(2)
            exSize[i] -= 1
        }
        getWeb("rus_0_0.html")
    }

    // Загрузка страницы
    private fun getWeb(file: String) {
        val tvWeb: WebView = findViewById(R.id.tv_web)
        tvWeb.webViewClient = WebViewClient()
        tvWeb.canGoBack()
        tvWeb.apply {
            settings.allowFileAccess = true
            loadUrl("file:///data/data/com.chelgames.egeintheroad/files/$file")
            settings.javaScriptEnabled = true
        }
    }

    private fun addInstruction(){
        val File_name = "rus_0_0.html"
        val text = "Ответ сохраняется только после нажатия на кнопку \"Принять\""
        val fos = openFileOutput(File_name, Context.MODE_PRIVATE)
        val outputWriter = OutputStreamWriter(fos)
        outputWriter.write(text)
        outputWriter.close()
    }

    // Переход к следующему заданию
    fun nextClick(view: View){
        if(Page == 26){
            val intent = Intent(this, result::class.java).apply{
                putExtra("result", trueAnswers)
                putExtra("ans", answers)
            }
            startActivity(intent)
        }
        if(Page < 26) {
            Page++
            if (move == 0) {
                numbers.add((0..exSize[Page]).random())
                var File_name = "rus_${Page}_${numbers[Page]}.html"
                var fis = openFileInput(File_name)
                var ir = InputStreamReader(fis)
                var text = ir.readText()
                while(text.contains("Источник: РЕШУ ЕГЭ") || !text.contains("Источник:")){
                    numbers[Page]  = (0..exSize[Page]).random()
                    File_name = "rus_${Page}_${numbers[Page]}.html"
                    fis = openFileInput(File_name)
                    ir = InputStreamReader(fis)
                    text = ir.readText()
                }
                if(text.contains("Ответ: ")){
                    var x = text.substringAfter("Ответ: ")
                    x = x.substringBefore("</span>")
                    answers.add(x)
                    ir.close()
                    text = text.replace("Ответ: $x", "")
                    var fos = openFileOutput(File_name, Context.MODE_PRIVATE)
                    var outputWriter = OutputStreamWriter(fos)
                    outputWriter.write(text)
                    outputWriter.close()
                    File_name = "rus_${Page}_${numbers[Page]}_a.html"
                    fos = openFileOutput(File_name, Context.MODE_PRIVATE)
                    outputWriter = OutputStreamWriter(fos)
                    outputWriter.write(x)
                    outputWriter.close()
                }
                else{
                    File_name = "rus_${Page}_${numbers[Page]}_a.html"
                    fis = openFileInput(File_name)
                    ir = InputStreamReader(fis)
                    text = ir.readText()
                    answers.add(text)
                }
            }
            else
                move--
            getWeb("rus_${Page}_${numbers[Page]}.html")
            tvPages?.setText("$Page/26")
        }
        if(Page == 26) {
            val tvBut = findViewById<Button>(R.id.nextBut)
            tvBut.setText("Закончить")
        }
    }

    // Переход на предыдущее задание
    fun prevClick(view: View){
        if(Page == 26){
            val tvBut = findViewById<Button>(R.id.nextBut)
            tvBut.setText("Далее")
        }
        if(Page > 0){
            move++
            Page--
            getWeb("rus_${Page}_${numbers[Page]}.html")
            tvPages?.setText("$Page/26")
        }
    }

    fun applyClick(view: View){
        // Сравнение ответов
        if(trueAnswers[Page] == 0 || trueAnswers[Page] == 1)
            return
        val tvText = findViewById<TextInputEditText>(R.id.tvText)
        val text = tvText.text.toString()
        var answer = answers[Page]
        val text2 = answer
        val ans = ArrayList<String>()
        if(answer.contains("|")){
            while(answer.contains("|")){
                ans.add(answer.substringBefore("|"))
                answer = answer.substringAfter("|")
            }
        }
        ans.add(answer)
        var b = 0
        ans.forEach{
            if(it == text)
                b++
        }
        if(b == 1){
            Toast.makeText(this, "Ответ $text верный", Toast.LENGTH_SHORT).show()
            trueAnswers[Page] = 1
            tvText.setText("")
        }
        else{
            Toast.makeText(this, "Ответ $text не верный, правильный ответ $text2", Toast.LENGTH_SHORT).show()
            trueAnswers[Page] = 0
            tvText.setText("")
        }
    }

    // Обработка нажатия на кнопку "Назад"
    private var exit = false
    override fun onBackPressed(){
        if (exit) this.finish() else {
            Toast.makeText(this, "Нажмите еще раз чтобы выйти.",
                    Toast.LENGTH_SHORT).show()
            exit = true
            Handler().postDelayed(Runnable { exit = false }, 2000)
        }
    }
}



