package com.newchar.woolhelper.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.newchar.woolhelper.db.SQLHelper;
import com.newchar.woolhelper.entry.ActionEntry;

/**
 * @author newChar
 * date 2021/6/17
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public final class SQLUtil {


    /**
     * 更新数据库中的 Action 对象
     * 没有则调用插入函数
     */
    public static void updateDBAction(Context ctx, ActionEntry action) {
        SQLHelper helpers = new SQLHelper(ctx);
        SQLiteDatabase database = helpers.getWritableDatabase();

        if (database.isOpen()) {

//            Cursor rawQuery = database.rawQuery(
//                    "SELECT id FROM ${SQLHelper.NAME} WHERE id=?",
//                    arrayOf(action.id)
//            )
            ContentValues tableContent = new ContentValues();
            tableContent.put("id", action.getId());
            tableContent.put("_id", action.get_id());
            tableContent.put("action", action.getAction());
            tableContent.put("actionType", action.getActionType());
            tableContent.put("enableState", action.getEnableState());
            tableContent.put("pageName", action.getPageName());
            tableContent.put("packageName", action.getPackageName());
            tableContent.put("open", action.getOpen());
            tableContent.put("desc", action.getDesc());
            tableContent.put("name", action.getName());

//            if (rawQuery.getCount() > 0) {       //有内容修改
//                database.update(SQLHelper.NAME, tableContent, "id", arrayOf(action.getId()));
//            } else {
//                database.insert(SQLHelper.NAME, "id", tableContent);
//            }
//            rawQuery.close();
            database.setTransactionSuccessful();
//            database.close();
        }
    }


}
