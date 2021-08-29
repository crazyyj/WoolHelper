package com.newchar.accesshelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.newchar.accesshelper.db.SQLHelper;
import com.newchar.accesshelper.entry.HasOrderAppItem;

/**
 * @author newChar
 * date 2021/7/5
 * @since Mock数据
 * @since 迭代版本，（以及描述）
 */
public class MockUtils {

    public static void saveFalseCmdAppData(int num) {
        SQLHelper helper = new SQLHelper(Utils.getContext());
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            for (int i = 0; i < num; i++) {
                ContentValues content = new ContentValues();
//                content.put(HasOrderAppItem.COLUMNS.COLUMNS_APP_ICON, R.mipmap.ic_launcher);
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
