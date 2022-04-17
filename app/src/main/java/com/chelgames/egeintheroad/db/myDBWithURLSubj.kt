package com.chelgames.egeintheroad.db

import android.provider.BaseColumns

object MyDBWithURLSubj {
    const val TABLE_NAME = "UrlOnTheme"
    const val COLUMN_NAME_SUBJECT = "subject"
    const val COLUMN_NAME_EXERCISE = "exercise"
    const val COLUMN_NAME_URL = "url"
    const val COLUMN_NAME_SIZE = "size"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "myDataBase.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "$COLUMN_NAME_SUBJECT TEXT," +
                    "$COLUMN_NAME_EXERCISE TEXT,"+
                    "$COLUMN_NAME_URL TEXT,"+
                    "$COLUMN_NAME_SIZE INTEGER)"
    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}