package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.view.accessibility.AccessibilityEvent;

import java.io.File;


/**
 * @author newChar
 * date 2021/3/29
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
class AccessManager {
    private final static  int INIT_LOAD = 1;
    private final static int INIT_ACCESS_INFO = 2;

    private Handler mHandler;
    private ServiceInfoChangeListener serviceInfoListener = null;
    private Context mContext;
    private Handler.Callback eventCallBack = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_LOAD: {
                    loadContent();
                }
                break;
                case INIT_ACCESS_INFO:
                    parsePkgInfo();
                    break;
            }
            return false;
        }
    };

    public AccessManager(Context context) {
        mContext = context;
        HandlerThread tempThread = new HandlerThread(
                "AccessManager InitThread",
                Process.THREAD_PRIORITY_BACKGROUND
        );
        tempThread.start();
        mHandler = new Handler(tempThread.getLooper(), eventCallBack);
    }

    public void init() {
        //加载本地环境
        mHandler.sendEmptyMessage(INIT_LOAD);

    }


    private void  parsePkgInfo() {
//        val actionManager = ActionManager.getInstance();
//        val allEnablePackageName = actionManager.getPackageNameIfEnable()
//        val build = ServiceInfoCompat.Builder(AccessibilityServiceInfo())
//            .addPackages(allEnablePackageName)
//            .capability(1)
//            .eventType()
//            .feedBack()
//            .timeOut()
//            .build()
//        serviceInfoListener?.onServiceInfoChange(build)
    }

    public void setServiceInfoChangeListener(ServiceInfoChangeListener info ) {
        this.serviceInfoListener = info;
    }

    public interface ServiceInfoChangeListener {
        void onServiceInfoChange(AccessibilityServiceInfo info);
    }


    public void release() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.getLooper().quitSafely();

        serviceInfoListener = null;
    }

    private void loadContent() {
        File ruleFile = new File(mContext.getExternalFilesDir(""), "access.json");
        String ruleFileJson = Utils.safeReadFileToText(ruleFile);
        ActionManager actionManager = ActionManager.getInstance();
        actionManager.loadActions(ruleFileJson);
    }

    public void onAccessibilityEvent(AccessibilityService service, AccessibilityEvent event) {
        ActionManager actionManager = ActionManager.getInstance();
        if (actionManager.searchPageMatchForEvent(event)) {
            actionManager.execute(service, event);
        }
    }

}
