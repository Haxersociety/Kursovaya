package com.chelgames.egeintheroad

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class statistic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        var tvStat = findViewById<TextView>(R.id.tvStats)
        val sPref = getSharedPreferences("Statistic", Context.MODE_PRIVATE)
        var cnt = sPref.getInt("count", 0)
        var mxscr = sPref.getInt("maxScore", 0)
        var avrgscr = sPref.getFloat("averageScore", 0.0f)
        var text = "Прорешено вариантов - $cnt\n\n" +
                "Средний результат - $avrgscr\n\n" +
                "Лучший результат - $mxscr правильных ответов"
        tvStat.setText(text)
    }
}