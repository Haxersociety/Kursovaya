package com.chelgames.egeintheroad.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDBManager(context: Context) {
    val myDBHelper = MyDBHelper(context)
    var db:SQLiteDatabase? = null

    fun openDB(){
        db = myDBHelper.writableDatabase
    }

    //Заполнение бд ответами. Пример ответа: "110010111110010110001011", где 1 - правильный ответ, 0 - неправильный.
    fun insertToDB(answer: String){
        val values = ContentValues().apply{
            put(MyDBWithURLSubj.COLUMN_NAME_ANSWER, answer)
        }

        db?.insert(MyDBWithURLSubj.TABLE_NAME, null, values)
    }

    fun deleteFromDB(id: Int){
        db?.delete(MyDBWithURLSubj.TABLE_NAME, "_ID = " + id, null)
    }

    fun readDBData(): ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDBWithURLSubj.TABLE_NAME, null, null,
                null, null, null, null)
        with(cursor){
            while(this?.moveToNext()!!){
                val dataAnswer = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_ANSWER))
                dataList.add("$dataAnswer")
            }
        }
        cursor?.close()
        return dataList
    }
}