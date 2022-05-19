package com.chelgames.egeintheroad

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.chelgames.egeintheroad.db.MyDBManager
import com.chelgames.egeintheroad.db.MyDBManager2
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickOnStart(view: View) {
        val intent = Intent(this, Exercises::class.java)
        startActivity(intent)
    }

    fun clickOnStat(view: View) {
        val intent2 = Intent(this, Statistic::class.java)
        startActivity(intent2)
    }

}
