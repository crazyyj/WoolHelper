package com.newchar.woolhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.newchar.woolhelper.applist.AppListActivity;
import com.newchar.woolhelper.main.MainActivity;

/**
 * @author newChar
 * date 2021/6/17
 * @since 路由方法类
 * @since 迭代版本，（以及描述）
 */
public final class RouterNav {

    private RouterNav() {
    }

    /**
     * 打开App列表页面
     *
     * @param ctx Activity
     */
    public static void goAppListPage(Activity ctx) {
        AppListActivity.launch(ctx);
    }

    /**
     * 打开无障碍设置页面
     *
     * @param context Context
     */
    public static void goAccessibilitySettingsPage(Context context) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
