package com.chelgames.egeintheroad.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper2(context: Context): SQLiteOpenHelper(context, MyDBWithURLSubj2.DATABASE_NAME, null, MyDBWithURLSubj2.DATABASE_VERSION) {
    // Создание таблицы
    override fun onCreate(db: SQLiteDatabase?) {
        println(MyDBWithURLSubj2.CREATE_TABLE)
        db?.execSQL(MyDBWithURLSubj2.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MyDBWithURLSubj2.DELETE_TABLE)
        onCreate(db)
    }
}