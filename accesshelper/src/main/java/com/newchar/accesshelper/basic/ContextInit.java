package com.newchar.accesshelper.basic;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.newchar.accesshelper.Utils;
import com.newchar.accesshelper.appinfo.AppInfoManager;

/**
 * @author newChar
 * date 2021/8/25
 * @since 用作初始化
 * @since 迭代版本，（以及描述）
 */
public class ContextInit extends ContentProvider {

    @Override
    public boolean onCreate() {
        Context applicationContext = getContext().getApplicationContext();
        Utils.holdContext(applicationContext);
        AppInfoManager.get().preLoad();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
