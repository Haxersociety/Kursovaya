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
}
