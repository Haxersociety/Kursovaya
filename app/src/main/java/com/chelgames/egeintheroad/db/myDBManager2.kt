package com.chelgames.egeintheroad.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class MyDBManager2(context: Context) {
    val myDBHelper = MyDBHelper2(context)
    var db:SQLiteDatabase? = null

    fun openDB(){
        db = myDBHelper.writableDatabase
    }

    fun getReadableDatabase(){
        myDBHelper.readableDatabase
    }

    fun insertToDB(exercise: Int){
        val values = ContentValues().apply{
            put(MyDBWithURLSubj2.COLUMN_NAME_EXERCISE, exercise)
        }

        db?.insert(MyDBWithURLSubj2.TABLE_NAME, null, values)
    }

    fun deleteFromDB(id: Int){
        db?.delete(MyDBWithURLSubj2.TABLE_NAME, "_ID = " + id, null)
    }

    fun readDBData(): ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDBWithURLSubj2.TABLE_NAME, null, null,
            null, null, null, null)
        with(cursor){
            while(this?.moveToNext()!!){
                val dataExercise = cursor?.getInt(cursor.getColumnIndex(MyDBWithURLSubj2.COLUMN_NAME_EXERCISE))
                val dataId = cursor?.getInt(cursor.getColumnIndex(BaseColumns._ID))
                if (dataExercise != null)
                    dataList.add("${dataId}/${dataExercise}")
            }
        }
        cursor?.close()
        return dataList
    }
}