package com.day.myreport.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object DatabaseContract {
    object TextEntry : BaseColumns {
        const val TABLE_NAME = "text_entries"
        const val COLUMN_NAME_TEXT = "text_column"
    }
}


class DbHelper(context: Context, private val callback: DatabaseCallback) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    interface DatabaseCallback {
        fun onTextInserted()
    }
    companion object {
        const val DATABASE_NAME = "report.db"
        const val DATABASE_VERSION = 1
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${DatabaseContract.TextEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.TextEntry.COLUMN_NAME_TEXT} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DatabaseContract.TextEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertText(text: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(DatabaseContract.TextEntry.COLUMN_NAME_TEXT, text)
        }
        db.insert(DatabaseContract.TextEntry.TABLE_NAME, null, values)
        callback.onTextInserted()
    }

    fun getAllTexts(): List<String> {
        val texts = ArrayList<String>()
        val selectQuery = "SELECT * FROM ${DatabaseContract.TextEntry.TABLE_NAME}"

        val db = readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val text = cursor.getString(cursor.getColumnIndex(DatabaseContract.TextEntry.COLUMN_NAME_TEXT))
                texts.add(text)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return texts
    }
}
