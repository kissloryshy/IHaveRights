package com.example.androidnav.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class UserInfo(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private var DATABASE_VER = 1
        private var DATABASE_NAME = "UserInfo"

        //Table UserInfo
        private var TABLE_NAME = "UserInfo"
        private var COL_NAME = "name"

        //Table Articles
        private var TABLE_NAME_ARTICLES = "articles"
        private var COL_CONTENT = "content"
        private var COL_SOURCE = "source"
        private var COL_TITLE = "title"
        private var COL_PUBLICATION_DATE = "publicationDate"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table if not exists $TABLE_NAME ($COL_NAME text);"
        db?.execSQL(sql)
    }

    //for new version db
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

    fun addArticle(content: String, source: String, title: String, publicationDate: String) {
        var sqlQuery = "create table if not exists $TABLE_NAME_ARTICLES " +
                "(id integer primary key autoincrement," +
                "$COL_CONTENT text, " +
                "$COL_SOURCE text, " +
                "$COL_TITLE text, " +
                "$COL_PUBLICATION_DATE datetime);"
        var db = this.writableDatabase
        db.execSQL(sqlQuery)

        var values = ContentValues()
        values.put(COL_CONTENT, content)
        values.put(COL_SOURCE, source)
        values.put(COL_TITLE, title)
        values.put(COL_PUBLICATION_DATE, publicationDate)
        db.insert(TABLE_NAME_ARTICLES, null, values)
        db.close()
    }

    fun getArticles() : ArrayList<String> {
        var list = ArrayList<String>()

        var sqlQuery = "select * from $TABLE_NAME_ARTICLES"
        var db = this.writableDatabase
        var cursor = db.rawQuery(sqlQuery, null)

        if(cursor.count != 0) {
            cursor.moveToFirst()
            list.add(cursor.getString(cursor.getColumnIndex(COL_CONTENT)))
            list.add(cursor.getString(cursor.getColumnIndex(COL_PUBLICATION_DATE)))
            list.add(cursor.getString(cursor.getColumnIndex(COL_SOURCE)))
            list.add(cursor.getString(cursor.getColumnIndex(COL_TITLE)))
        }

        return list
    }

    fun dropArticlesTable() {
        var sqlQuery = "drop table if exists $TABLE_NAME_ARTICLES";
        var db = this.writableDatabase
        db.execSQL(sqlQuery)
    }
}