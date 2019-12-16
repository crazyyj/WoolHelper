package com.newchar.woolhelper.db

import android.provider.BaseColumns
import android.provider.FontsContract

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-12-16
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class SQLUpdate {

    companion object {


        const val TABLE_NAME_ACTION_EVENT: String = "Action"
        const val CREATE_ACTION_EVENT: String = "CREATE TABLE ${TABLE_NAME_ACTION_EVENT} ${FontsContract.Columns._ID}(integer 10), "

    }



}