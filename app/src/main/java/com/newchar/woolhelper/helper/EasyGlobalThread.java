package com.newchar.woolhelper.helper;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;

/**
 * @author newChar
 * date 2021/6/18
 * @since 全局使用，在Activity内，容易造成泄漏，不能remove msg 会误删其他msg
 * @since 迭代版本，（以及描述）
 */
public class EasyGlobalThread extends HandlerThread {

    private EasyGlobalThread() {
        super("EasyGlobalThread #", Process.THREAD_PRIORITY_BACKGROUND);
        start();
    }

    public static Handler getEasyThreadHandler(Handler.Callback callback) {
        return new EasyGlobalThread().getThreadHandler(callback);
    }

    private Handler getThreadHandler(Handler.Callback callback) {
        return new Handler(getLooper(), callback);
    }

}
