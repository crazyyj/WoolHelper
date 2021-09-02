package com.newchar.woolhelper.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.Window;

import com.newchar.accesshelper.log.LLL;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author newChar
 * date 2021/9/1
 * @since 最基础版Base父类
 * @since 迭代版本，（以及描述）
 */
public abstract class BaseActivity extends Activity {

    private final AtomicBoolean mResReleased = new AtomicBoolean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(getIntent(), savedInstanceState);
        setContentView(getContentViewId());

        findViewById(Window.ID_ANDROID_CONTENT).getViewTreeObserver()
                .addOnGlobalLayoutListener(mGlobalLayoutListener);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initData(intent, null);
        globalLayoutInitFinish();
        setIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Activity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Activity", "onResume");
    }

    private final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = this::globalLayoutDataInit;

    /**
     * 初始化数据设置
     *
     * @param intent             上一个页面传递过来的数据
     * @param savedInstanceState 可能会出现的保存实例数据
     */
    protected abstract void initData(Intent intent, Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 获取LayoutId
     *
     * @return 布局Id
     */
    protected abstract int getContentViewId();


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();
    }

    private void globalLayoutDataInit() {
        findViewById(Window.ID_ANDROID_CONTENT).getViewTreeObserver()
                .removeOnGlobalLayoutListener(mGlobalLayoutListener);
        globalLayoutInitFinish();
    }

    private void globalLayoutInitFinish() {
        onGlobalLayoutInitFinish();
    }

    protected void onGlobalLayoutInitFinish() {
        LLL.e("Activity", "onGlobalLayoutInitFinish " + System.currentTimeMillis());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing() && mResReleased.compareAndSet(false, true)) {
            releaseRes();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mResReleased.compareAndSet(false, true)) {
            releaseRes();
        }
    }

    public void finish(int animIn, int animOut) {
        super.finish();
        this.overridePendingTransition(animIn, animOut);
    }

    protected void releaseRes() {
    }

}
