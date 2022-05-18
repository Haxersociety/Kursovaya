package com.chelgames.egeintheroad

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val intent = intent
        val trueAnswers = intent.getStringArrayListExtra("trueAnswers")
        val answers = intent.getStringArrayListExtra("answers")
        println("+++++++++++++++++++++")
        println(answers)
        println("_____________________")
        println(trueAnswers)
    }

    fun clickOnFinish(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}