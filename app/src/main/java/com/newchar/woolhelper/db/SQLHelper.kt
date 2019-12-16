package com.newchar.woolhelper.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 *  @author wenliqiang
 *  date 2019-12-01
 *  @since 当前版本，（以及描述）
 *  @since 迭代版本，（以及描述）
 */
class SQLHelper(var ctx: Context, var version: Int) : SQLiteOpenHelper(ctx, DB_NAME, null, version) {

    val VERSION_DEFAULT = 1
    val VERSION_DEFAULdT = 2

    companion object{
        val NAME = "ACTION_NAME"
        val DB_NAME = "AAAA"
        val CREATE_SQL_TABLE = "CREATE TABLE ${NAME} " +"{" + "_id varchar(30), id integer(10)" + "}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.run {
                beginTransaction()
                execSQL(CREATE_SQL_TABLE)
            }
        } finally {
            db?.run {
                setTransactionSuccessful()
                close()
            }
        }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when (newVersion) {
            VERSION_DEFAULT->{

            }
            VERSION_DEFAULdT -> {
            }
        }
    }

}