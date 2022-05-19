package com.chelgames.egeintheroad

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chelgames.egeintheroad.db.MyDBManager
import com.chelgames.egeintheroad.db.MyDBManager2


class Result : AppCompatActivity() {

    var result = ""
    var myDBManager = MyDBManager(this)
    var myDBManager2 = MyDBManager2(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val intent = intent
        val trueAnswers = intent.getStringArrayListExtra("trueAnswers")
        val answers = intent.getStringArrayListExtra("answers")

        val tvTable = findViewById<TableLayout>(R.id.tvTableLayout)

        val tableRows = ArrayList<TableRow>()

        if (answers != null && trueAnswers != null) {
            for (i in 0 until answers.size - 1) {
                var resultElement = "0"

                tableRows.add(TableRow(this))
                val textView1 = TextView(this)
                textView1.setText(trueAnswers[i]);

                val textView2 = TextView(this)
                textView2.setText(answers[i]);
                textView2.setTextColor(Color.parseColor("#aa0000"))

                tableRows[i].addView(
                    textView1, TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f
                    )
                )

                tableRows[i].addView(
                    textView2, TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f
                    )
                )

                val delimTrueAnswer = ArrayList<String>()
                if (trueAnswers[i].contains("|")) {
                    while (trueAnswers[i].contains("|")) {
                        delimTrueAnswer.add(trueAnswers[i].substringBefore("|"))
                        trueAnswers[i] = trueAnswers[i].substringAfter("|")
                    }
                } else {
                    delimTrueAnswer.add(trueAnswers[i])
                }
                for (answer in delimTrueAnswer) {
                    if (answer == answers[i].toLowerCase().replace(" ", "")) {
                        resultElement = "1"
                        textView2.setTextColor(Color.parseColor("#00aa00"))
                        break;
                    }
                }

                result += resultElement

            }
        }

        for (row in tableRows)
            tvTable.addView(row)

        println(result)

    }

    fun clickOnFinish(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        myDBManager.openDB()
        myDBManager.insertToDB(result)

        //Записываем номера заданий, которые неправильно решили
        myDBManager2.openDB()
        myDBManager2.getReadableDatabase();
        var i = 1
        for (ex in result) {
            if (ex == '0')
                myDBManager2.insertToDB(i)
            i++
        }

        startActivity(intent)
    }

}