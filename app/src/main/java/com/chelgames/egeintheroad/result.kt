package com.chelgames.egeintheroad

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val intent = intent
        val trueAnswers = intent.getIntegerArrayListExtra("result")
        val answers = intent.getStringArrayListExtra("ans")
        val tvAns = findViewById<TextView>(R.id.tvAnswers)
        val tvTrueAns = findViewById<TextView>(R.id.tvTrueAnswers)
        var text = ""
        var text2 = ""
        var count = 0
        for(i in 1..26){
            if(trueAnswers?.get(i) == 1) {
                text = "$text\n$i - Верно"
                count++
            }
            else
                text = "$text\n$i - Не верно"
            text2 = "$text2\n$i - ${answers?.get(i)}"
        }
        tvAns.setText(text2)
        tvTrueAns.setText(text)
        val sPref = getSharedPreferences("Statistic" ,Context.MODE_PRIVATE)
        var cnt = sPref.getInt("count", 0)
        var mxscr = sPref.getInt("maxScore", 0)
        var avrgscr = sPref.getFloat("averageScore", 0.0f)
        var smscr = avrgscr*cnt
        smscr += count
        if(mxscr < count)
            mxscr = count
        cnt++
        avrgscr = smscr/cnt
        val ed = sPref.edit()
        ed.putInt("count", cnt)
        ed.putFloat("averageScore", avrgscr)
        ed.putInt("maxScore", mxscr)
        ed.apply()
    }

    fun clickOnFinish(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}