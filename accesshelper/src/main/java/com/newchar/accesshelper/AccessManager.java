package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.newchar.accesshelper.compat.ServiceInfoCompat;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Set;


/**
 * @author newChar
 * date 2021/3/29
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
class AccessManager {
    private final static int INIT_LOAD = 1;
    private final static int INIT_ACCESS_INFO = 2;

    private Handler mHandler;
    private ServiceInfoChangeListener serviceInfoListener = null;
    private WeakReference<AccessibilityService> mServiceRef;
    private Handler.Callback eventCallBack = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_LOAD: {
                    loadContent();
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//                        @Override
//                        public void run() {
                            parsePkgInfo();
//                        }
//                    });
                }
                break;
                case INIT_ACCESS_INFO:

                    break;
            }
            return false;
        }
    };

    public AccessManager(AccessibilityService service) {
        mServiceRef = new WeakReference<>(service);
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

    private void parsePkgInfo() {

        ActionManager actionManager = ActionManager.getInstance();
        Set<String> allEnablePackageName = actionManager.getPackageNameIfEnable();
        AccessibilityService accessibilityService = mServiceRef.get();
        if (accessibilityService == null) {
            return;
        }
        ServiceInfoCompat serviceInfoCompat = new ServiceInfoCompat(accessibilityService.getServiceInfo());
        for (String s : allEnablePackageName) {
            serviceInfoCompat.addPackage(s);
        }
        serviceInfoCompat.setDefaultEventType();
        serviceInfoCompat.setDefaultFeedBack();
        serviceInfoCompat.setTimeOut();
        serviceInfoCompat.addFlags(AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS);
        serviceInfoCompat.addFlags(AccessibilityServiceInfo.FLAG_REQUEST_FILTER_KEY_EVENTS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            serviceInfoCompat.addFlags(AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS);
        }
        if (serviceInfoListener != null) {
            serviceInfoListener.onServiceInfoChange(serviceInfoCompat.get());
        }
    }

    public void setServiceInfoChangeListener(ServiceInfoChangeListener info) {
        this.serviceInfoListener = info;
    }

    public interface ServiceInfoChangeListener {
        void onServiceInfoChange(AccessibilityServiceInfo info);
    }


    private void loadContent() {
        AccessibilityService accessibilityService = mServiceRef.get();
        if (accessibilityService == null) {
            Log.e("Server", "AccessibilityService is null。");
            return;
        }
        File ruleFile = new File(accessibilityService.getCacheDir(), "access.json");
        String ruleFileJson = Utils.safeReadFileToText(ruleFile);
        ActionManager actionManager = ActionManager.getInstance();
        actionManager.loadActions(ruleFileJson);
    }

    public void onAccessibilityEvent(AccessibilityService service, AccessibilityEvent event) {
        ActionManager actionManager = ActionManager.getInstance();
        if (actionManager.searchActionMatchForEvent(event)) {
            actionManager.execute(service, event);
        }
    }


    public void release() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.getLooper().quitSafely();

        serviceInfoListener = null;
    }


}
