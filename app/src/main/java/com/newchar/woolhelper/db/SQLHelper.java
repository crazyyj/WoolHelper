package com.newchar.woolhelper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.newchar.woolhelper.entry.HasOrderAppItem;

/**
 * @author newChar
 * date 2021/6/18
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class SQLHelper extends SQLiteOpenHelper {

    /**
     * 初始化版本
     */
    private static final int VERSION_INIT = 1;

    public SQLHelper(Context context) {
        super(context, "cmd", null, VERSION_INIT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(HasOrderAppItem.TABLE.createTableSql());
            db.setTransactionSuccessful();
            Log.d("SQLHelper", " cmd 开始创建");
        } finally {
            db.endTransaction();
            Log.d("SQLHelper", "cmd 创建完成");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
