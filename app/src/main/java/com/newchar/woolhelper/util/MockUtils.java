package com.newchar.woolhelper.util;

import android.content.ContentValues;
import android.content.Entity;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import com.newchar.woolhelper.App;
import com.newchar.woolhelper.R;
import com.newchar.woolhelper.db.SQLHelper;
import com.newchar.woolhelper.entry.HasOrderAppItem;

/**
 * @author newChar
 * date 2021/7/5
 * @since Mock数据
 * @since 迭代版本，（以及描述）
 */
public class MockUtils {

    public static void saveFalseCmdAppData(int num) {
        SQLHelper helper = new SQLHelper(App.mApplication);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            for (int i = 0; i < num; i++) {
                ContentValues content = new ContentValues();
                content.put(HasOrderAppItem.COLUMNS.COLUMNS_APP_ICON, R.mipmap.ic_launcher);
                content.put(HasOrderAppItem.COLUMNS.COLUMNS_APP_PACKAGE_NAME, "com.baidu.ar." + System.currentTimeMillis());
                content.put(HasOrderAppItem.COLUMNS.COLUMNS_ORDER_NUMBER, "这里是jige");
                content.put(HasOrderAppItem.COLUMNS.COLUMNS_DESCRIBE, "这里是描述");
                writableDatabase.insert(HasOrderAppItem.TABLE.NAME, null, content);
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }


}
