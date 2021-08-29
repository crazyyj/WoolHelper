package com.newchar.woolhelper.adapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.newchar.accesshelper.entry.HasOrderAppItem;
import com.newchar.woolhelper.R;

/**
 * @author newChar
 * date 2021/7/5
 * @since 命令App列表
 * @since 迭代版本，（以及描述）
 */
public class AllAppAdapter extends SimpleCursorAdapter {

    public AllAppAdapter(Context context, Cursor cursor) {
        super(context, R.layout.item_main_cmd_app_lsit, cursor
                , HasOrderAppItem.COLUMNS.getAllColumns()
                , new int[]{ R.id.ivAppIcon, R.id.tvAppPkgName, R.id.tvAppCmdNumber, R.id.tvAppDesc}
                , CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

    }


}
