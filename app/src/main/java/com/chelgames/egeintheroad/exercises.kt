package com.chelgames.egeintheroad

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chelgames.egeintheroad.db.MyDBManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.OutputStreamWriter

class exercises: AppCompatActivity() {
    private val myDBManager = MyDBManager(this)
    private var ex2 = 0
    private var lastEx = 0
    private var lastExSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)
    }

    private fun getAttr(){

    }

    // Создание нового варианты из случайных заданий.
    fun createVariant(view: View){
        val intent = Intent(this, work::class.java)
        startActivity(intent)
    }

    // Подбор заданий на основе предыдущих вариантов.
    fun select_tasks(view: View){
        val intent = Intent(this, work::class.java)
        startActivity(intent)
    }
}