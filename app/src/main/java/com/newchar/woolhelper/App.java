package com.newchar.woolhelper;

import android.app.Application;

/**
 * @author newChar
 * date 2021/4/13
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class App extends Application {

    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

//        Handler easyThreadHandler = EasyGlobalThread.getEasyThreadHandler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                return false;
//            }
//        });
//        easyThreadHandler.sendEmptyMessage(1);
    }

}
