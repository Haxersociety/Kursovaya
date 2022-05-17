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


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickOnStart(view: View) {
        val intent = Intent(this, exercises::class.java)
        startActivity(intent)
    }

    fun clickOnStat(view: View) {
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

}
