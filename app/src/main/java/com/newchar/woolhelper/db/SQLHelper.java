package com.newchar.woolhelper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author newChar
 * date 2021/6/18
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class SQLHelper extends SQLiteOpenHelper {

    public static final String CREATE_SQL_TABLE =
            "CREATE TABLE $NAME (_id varchar(30), id integer(10) )";

    public SQLHelper(Context context) {
        super(context, "DBNAME", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(CREATE_SQL_TABLE);
            db.setTransactionSuccessful();
        } finally {
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
