package com.newchar.woolhelper;

import android.app.Activity;
import android.content.Intent;

import com.newchar.woolhelper.main.MainActivity;

/**
 * @author newChar
 * date 2021/6/17
 * @since 当前版本，（以及描述）
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
        Intent intent = new Intent(ctx, MainActivity.class);
        ctx.startActivityForResult(intent, 20);
    }


}
