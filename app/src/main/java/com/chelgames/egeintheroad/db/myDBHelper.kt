package com.chelgames.egeintheroad.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class myDBHelper(context: Context): SQLiteOpenHelper(context, myDBWithURLSubj.DATABASE_NAME, null, myDBWithURLSubj.DATABASE_VERSION) {
    // Создание таблицы
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(myDBWithURLSubj.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(myDBWithURLSubj.DELETE_TABLE)
        onCreate(db)
    }
}