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
        myDBManager.openDB()
        getAttr()
    }

    private fun getAttr(){
        CoroutineScope(Dispatchers.IO).launch{
            val doc = Jsoup.connect("https://rus-ege.sdamgia.ru/prob_catalog").get()
            val elem: Elements
            elem = doc.getElementsByAttributeValue("class", "cat_show")
            val arr:ArrayList<String> = myDBManager.readDBSize()
            var x = arr.size - 1
            if(x == -1)
                x++
            else {
                if(x != 27)
                    myDBManager.deleteFromDB(x + 1)
            }
            for(i in x..67){
                if(x == 67)
                    break
                // Подключение к сайту и парсинг заданий
                val doc2 = Jsoup.connect("https://rus-ege.sdamgia.ru${elem[i].attr("href")}&print=true&attr1=true&ans=true").get()
                var elem2: Elements
                elem2 = doc2.getElementsByAttributeValue("class", "prob_nums")
                var ex = elem2[0].toString().substring(53)
                ex = ex.removeRange(ex.indexOf('&'), ex.length)
                var elem3: Elements
                elem3 = doc2.getElementsByAttributeValue("class", "prob_maindiv")
                ex2 = elem3.size
                //
                if(ex[0] != 'Д') {
                    if(lastEx != ex.toInt())
                        lastExSize = 0
                    // Запись в базу данных значений
                    myDBManager.insertToDB("rus", ex, elem[i].attr("href"), elem2.size)
                    lastEx = ex.toInt()
                    // Создание файлов с заданиями
                    for (j in lastExSize until lastExSize+elem3.size) {
                        val File_name = "rus_${lastEx}_$j.html"
                        var text = elem3[j-lastExSize].toString()
                        text = text.replace("/get_file", "https://rus-ege.sdamgia.ru/get_file")
                        val fos = openFileOutput(File_name, Context.MODE_PRIVATE)
                        val outputWriter = OutputStreamWriter(fos)
                        outputWriter.write(text)
                        outputWriter.close()
                    }
                    lastExSize += elem2.size
                }
            }
        }
    }
    // Переход на другое activity, в котором отображаются задания
    fun onClick1(view: View){
        val intent = Intent(this, work::class.java)
        startActivity(intent)
    }
}