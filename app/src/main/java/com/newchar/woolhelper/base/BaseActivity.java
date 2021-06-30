package com.newchar.woolhelper.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

import com.newchar.woolhelper.helper.EasyGlobalThread;

/**
 * @author newChar
 * date 2021/7/1
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public abstract class BaseActivity extends Activity {

    private static final int MSG_INFLATE_LAYOUT = -101;
    private static final int MSG_SET_CONTENT_VIEW = -201;

    private final Handler.Callback asyncHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INFLATE_LAYOUT:
                    View contentView = LayoutInflater.from(getApplicationContext())
                            .inflate(getContentView(), null);
                    handler.obtainMessage(MSG_SET_CONTENT_VIEW, contentView).sendToTarget();
                    break;
                case MSG_SET_CONTENT_VIEW:
                    if (msg.obj instanceof View) {
                        setContentView((View) msg.obj);
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    private final Handler inflateLayoutHandler = EasyGlobalThread.getEasyThreadHandler(asyncHandlerCallback);
    private final Handler handler = new Handler(Looper.getMainLooper(), asyncHandlerCallback);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateLayoutHandler.sendEmptyMessage(MSG_INFLATE_LAYOUT);

    }

    protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        inflateLayoutHandler.getLooper().quitSafely();
        inflateLayoutHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
