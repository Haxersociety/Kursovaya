package com.chelgames.egeintheroad.db

import android.provider.BaseColumns

object MyDBWithURLSubj {
    const val TABLE_NAME = "Answers"
    const val COLUMN_NAME_ANSWER = "answer"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "myDataBase.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "$COLUMN_NAME_ANSWER TEXT,"
    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}