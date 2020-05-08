package com.example.androidnav.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class UserInfo(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "UserInfo"

        //Table UserInfo
        private val TABLE_NAME = "UserInfo"
        private val COL_NAME = "name"

        //Table Articles
        private val TABLE_NAME_ARTICLES = "articles"
        private val COL_CONTENT = "content"
        private val COL_SOURCE = "source"
        private val COL_TITLE = "title"
        private val COL_PUBLICATION_DATE = "publicationDate"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table if not exists $TABLE_NAME ($COL_NAME text);"
        db?.execSQL(sql)

        var sqlQuery = "create table if not exists $TABLE_NAME_ARTICLES (id integer primary key autoincrement," +
                "$COL_CONTENT text," +
                "$COL_SOURCE text," +
                "$COL_TITLE text," +
                "$COL_PUBLICATION_DATE datetime);"
        db?.execSQL(sqlQuery)

        Log.d("kiss", "userInfo onCreate")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $TABLE_NAME;")
        onCreate(db!!)
    }

    fun saveName(name: String) {
        var db: SQLiteDatabase = this.writableDatabase

        //Clear name
        var query = "delete from UserInfo;"
        db.execSQL(query)

        //Add name
        var values = ContentValues()
        values.put(COL_NAME, name)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getName() : String {
        var query = "select name from UserInfo"
        var db: SQLiteDatabase = this.writableDatabase
        var cursor: Cursor = db.rawQuery(query, null)

        return if (cursor.count != 0) {
            cursor.moveToFirst()
            cursor.getString(cursor.getColumnIndex("name"))
        } else {
            ""
        }
    }
}