package com.example.todo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,  DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " TEXT PRIMARY KEY, " +
                TITLE_COL + " TEXT," +
                PRIORITY_COL + " TEXT" + ")")

//        UPDATE `sqlite_sequence` SET `seq` = 0 WHERE `name` = 'table_name';
//        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '$TABLE_NAME'");
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertTask(task: CardInfo): Long {
        val values = ContentValues()
        values.put(ID_COL, task.id)
        values.put(TITLE_COL, task.title)
        values.put(PRIORITY_COL, task.priority)
        val db = this.writableDatabase
        val a = db.insert(TABLE_NAME, null, values)
//        db.close()
        return a
    }

    fun getTasks(): List<CardInfo> {
        val db = this.readableDatabase

        var cursor: Cursor? = null
        cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        var listData = mutableListOf<CardInfo>()
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getString(0)
                val title = cursor.getString(1)
                val priority = cursor.getString(2)
                listData.add(CardInfo(title, priority, id))
            }while (cursor.moveToNext())
        }
        cursor.close()
//        db.close()
        return listData
    }

    fun updateTask(id: String, title: String, priority: String) {
        val values = ContentValues()
        values.put(ID_COL, id)
        values.put(TITLE_COL, title)
        values.put(PRIORITY_COL, priority)

        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "id=?", arrayOf(id))
//        db.close()
    }

    fun deleteTask(id: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "id=?", arrayOf(id))
//        db.close()
    }

    companion object{
        // here we have defined variables for our database

        private const val DATABASE_NAME = "TO_DO"

        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "to_do_table"

        const val ID_COL = "id"

        const val TITLE_COL = "title"

        const val PRIORITY_COL = "priority"
    }
}