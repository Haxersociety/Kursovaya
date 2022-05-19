package com.chelgames.egeintheroad

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chelgames.egeintheroad.db.MyDBManager

class Exercises: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)
    }

    // Создание нового варианты из случайных заданий.
    fun createVariant(view: View){
        val intent = Intent(this, WorkVariant::class.java)
        startActivity(intent)
    }

    // Подбор заданий на основе предыдущих вариантов.
    fun selectTasks(view: View){
        val intent = Intent(this, WorkSelect::class.java)
        startActivity(intent)
    }
}