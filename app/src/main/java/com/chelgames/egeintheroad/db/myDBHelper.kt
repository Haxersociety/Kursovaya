package com.chelgames.egeintheroad.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context): SQLiteOpenHelper(context, MyDBWithURLSubj.DATABASE_NAME, null, MyDBWithURLSubj.DATABASE_VERSION) {
    // Создание таблицы
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MyDBWithURLSubj.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MyDBWithURLSubj.DELETE_TABLE)
        onCreate(db)
    }
}