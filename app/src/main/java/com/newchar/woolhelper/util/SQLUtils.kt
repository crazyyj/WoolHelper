package com.newchar.woolhelper.util

import android.content.ContentValues
import android.content.Context
import com.newchar.woolhelper.db.SQLHelper
import com.newchar.woolhelper.entry.ActionEntry

/**
 *  @author wenliqiang
 *  date 2019-12-15
 *  @since 数据库操作工具类，最外层包装
 *  @since 迭代版本，（以及描述）
 */
class SQLUtils {

    companion object{

        /**
         * 更新数据库中的 Action 对象
         * 没有则调用插入函数
         */
        fun updateDBAction(ctx: Context, action: ActionEntry) {
            val helpers = SQLHelper(ctx, 2)
            val database = helpers.writableDatabase
            if (database.isOpen) {

                var rawQuery = database.rawQuery(
                    "SELECT id FROM ${SQLHelper.NAME} WHERE id=?",
                    arrayOf(action.id)
                )
                var tableContent = ContentValues()
                tableContent.put("id", action.id)
                tableContent.put("_id", action._id)
                tableContent.put("action", action.action)
                tableContent.put("actionType", action.actionType)
                tableContent.put("enableState", action.enableState)
                tableContent.put("pageName", action.pageName)
                tableContent.put("packageName", action.packageName)
                tableContent.put("open", action.open)
                tableContent.put("desc", action.desc)
                tableContent.put("name", action.name)

                if (rawQuery.count > 0) {       //有内容修改
                    database.update(SQLHelper.NAME, tableContent, "id", arrayOf(action.id))
                } else {
                    database.insert(SQLHelper.NAME, "id", tableContent)
                }
                database.setTransactionSuccessful()
                database.close()
            }
        }

//        fun getAllEnableAction(): MutableList<ActionEntry> {
////            val helpers = SQLHelper(null, 2)
//            val readableDatabase = helpers.readableDatabase
//            val rawQuery = readableDatabase.rawQuery("SELECT * FROM ${SQLHelper.NAME}", arrayOf(""))
//            var allEnableAction = ArrayList<ActionEntry>()
//            val action = ActionEntry()
//            for (index in 0..rawQuery.count) {
//                action.id = rawQuery.getString(rawQuery.getColumnIndex("id"))
//                action._id = rawQuery.getInt(rawQuery.getColumnIndex("_id"))
//// TODO 获取全部字段
//                allEnableAction.add(action)
//            }

//            return allEnableAction
//        }

    }

}