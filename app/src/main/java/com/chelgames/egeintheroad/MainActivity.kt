package com.chelgames.egeintheroad

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickOnStart(view: View){
        val intent = Intent(this, exercises::class.java)
        startActivity(intent)
    }

    fun clickOnStat(view: View){
        val intent2 = Intent(this, statistic::class.java)
        startActivity(intent2)
    }

    //TODO Переделать БД.

    //TODO Переделать интерфейс.

    //TODO Провести рефактор кода, оставить только необходимые функции (Сомневаюсь что вообще что-либо останется).

    //TODO Распарсить сайт Яндекса на задачи
    //https://yandex.ru/tutor/subject/tag/problems/?ege_number_id=283&tag_id=19
    //Версия для печати:
    //https://yandex.ru/tutor/subject/tag/problems/?ege_number_id=283&print=1&tag_id=19
}
