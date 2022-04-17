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

    // Заполнение бд "Предмет|Номер|Ссылка|Количество заданий"
    fun insertToDB(subject: String, exercise: String, url: String, size: Int){
        val values = ContentValues().apply{
            put(MyDBWithURLSubj.COLUMN_NAME_SUBJECT, subject)
            put(MyDBWithURLSubj.COLUMN_NAME_EXERCISE, exercise)
            put(MyDBWithURLSubj.COLUMN_NAME_URL, url)
            put(MyDBWithURLSubj.COLUMN_NAME_SIZE, size)
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
                val dataUrl = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_URL))
                val dataSubj = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_SUBJECT))
                val dataEx = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_EXERCISE))
                val dataSz = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_SIZE))
                dataList.add("$dataSubj+$dataEx+$dataUrl+$dataSz")
            }
        }
        cursor?.close()
        return dataList
    }

    // Функция возвращающая только номер задания, и количество заданий в нем. В нее передается предмет, с которым
    // мы работаем
    fun readDBDataBySubj(sb: String): ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDBWithURLSubj.TABLE_NAME, null, null,
                null, null, null, null)
        with(cursor){
            while(this?.moveToNext()!!){
                val dataSubj = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_SUBJECT))
                val dataEx = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_EXERCISE))
                val dataSz = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_SIZE))
                if (sb == dataSubj.toString()){
                    dataList.add("$dataEx+$dataSz")
                }
            }
        }
        cursor?.close()
        return dataList
    }

    // Считывание предметов которые хранятся в бд
    fun readDBSubj(): ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDBWithURLSubj.TABLE_NAME, null, null,
                null, null, null, null)
        with(cursor){
            while(this?.moveToNext()!!){
                val dataSubj = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_SUBJECT))
                dataList.add("$dataSubj")
            }
        }
        cursor?.close()
        return dataList
    }

    fun readDBSize(): ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDBWithURLSubj.TABLE_NAME, null, null,
                null, null, null, null)
        with(cursor){
            while(this?.moveToNext()!!){
                val dataSize = cursor?.getString(cursor.getColumnIndex(MyDBWithURLSubj.COLUMN_NAME_SIZE))
                dataList.add("$dataSize")
            }
        }
        cursor?.close()
        return dataList
    }
}