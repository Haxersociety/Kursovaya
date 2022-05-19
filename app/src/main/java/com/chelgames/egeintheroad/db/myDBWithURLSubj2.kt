package com.chelgames.egeintheroad.db

import android.provider.BaseColumns

object MyDBWithURLSubj2 {
    const val TABLE_NAME = "NotCorrectlySolved"
    const val COLUMN_NAME_EXERCISE = "exercise"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "myDataBase2.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                    "$COLUMN_NAME_EXERCISE INTEGER)"
    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}