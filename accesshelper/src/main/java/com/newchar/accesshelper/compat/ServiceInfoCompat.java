package com.newchar.accesshelper.compat;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author newChar
 * date 2021/3/18
 * @since AccessibilityServiceInfo 构建类
 * <?xml version="1.0" encoding="utf-8"?>
 * <accessibility-service xmlns:android="http://schemas.android.com/apk/res/android"
 * android:accessibilityEventTypes="typeAllMask"
 * android:accessibilityFeedbackType="feedbackGeneric"
 * android:accessibilityFlags="flagRetrieveInteractiveWindows|flagRequestFilterKeyEvents"
 * android:canRequestFilterKeyEvents="true"
 * android:canRetrieveWindowContent="true"
 * android:description="@string/app_name"
 * android:notificationTimeout="100"
 * android:packageNames="com.xxx.xxx" />
 * @since 无法构建分类信息，描述信息等。
 */
public class ServiceInfoCompat {

    private final AccessibilityServiceInfo mServerInfo;

    public ServiceInfoCompat() {
        this(new AccessibilityServiceInfo());
    }

    /**
     * 保持使用外部传入的ServerInfo，保持数据的一致性，该类目的是修改，增加信息
     *
     * @param serverInfo AccessibilityServiceInfo
     */
    public ServiceInfoCompat(AccessibilityServiceInfo serverInfo) {
        if (null != serverInfo) {
            this.mServerInfo = serverInfo;
        } else {
            this.mServerInfo = new AccessibilityServiceInfo();
        }
    }

    public void addPackage(String pkgName) {
        if (null == pkgName) {
            return;
        }
        if (null == mServerInfo.packageNames) {
            mServerInfo.packageNames = new String[]{pkgName};
        } else {
            String[] newPackageNames = new String[mServerInfo.packageNames.length + 1];
            System.arraycopy(mServerInfo.packageNames, 0, newPackageNames, 0, mServerInfo.packageNames.length);
            newPackageNames[newPackageNames.length - 1] = pkgName;
            mServerInfo.packageNames = newPackageNames;
        }
    }

    public void setDefaultEventType() {
        mServerInfo.eventTypes = AccessibilityEvent.TYPE_WINDOWS_CHANGED | AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED | AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
    }

    public void addEventType(int... eventType) {
        if (eventType != null && eventType.length > 0) {
            for (int type : eventType) {
                mServerInfo.eventTypes |= type;
            }
        }
    }

    public void setEventType(int... eventType) {
        mServerInfo.eventTypes = 0;
        addEventType(eventType);
    }

    public void setDefaultFeedBack() {
        mServerInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
    }

    /**
     * @param feedBack 反馈类型
     * @see AccessibilityServiceInfo#FEEDBACK_SPOKEN
     * @see AccessibilityServiceInfo#FEEDBACK_HAPTIC
     * @see AccessibilityServiceInfo#FEEDBACK_AUDIBLE
     * @see AccessibilityServiceInfo#FEEDBACK_VISUAL
     * @see AccessibilityServiceInfo#FEEDBACK_GENERIC
     * @see AccessibilityServiceInfo#FEEDBACK_BRAILLE
     */
    public void setFeedBack(int feedBack) {
        mServerInfo.feedbackType = feedBack;
    }

    /**
     * 超时时间
     */
    public void setTimeOut(long timeOut) {
        mServerInfo.notificationTimeout = timeOut;
    }

    /**
     * 超时时间
     */
    public void setTimeOut() {
        setTimeOut(10L);
    }

    /**
     * 设置Flag
     *
     * @param flag 描述
     */
    public void setFlags(int flag) {
        try {
            mServerInfo.flags = flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Flag
     *
     * @param flag 描述
     */
    public void addFlags(int flag) {
        try {
            mServerInfo.flags |= flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Flag
     *
     * @param flags Flag
     */
    public void addFlags(int... flags) {
        if (flags != null && flags.length > 0) {
            for (int flag : flags) {
                mServerInfo.flags |= flag;
            }
        }
    }

    /*
     * 设置他在 辅助服务设置界面的描述， 无效
     *
     * @param description 描述
     */
//    public void setDescription(String description) {
//        if (TextUtils.isEmpty(description)) {
//            return;
//        }
//        try {
//            Class<? extends AccessibilityServiceInfo> serverClass = mServerInfo.getClass();
//            Field mNonLocalizedDescriptionField = serverClass.getDeclaredField("mNonLocalizedDescription");
//            mNonLocalizedDescriptionField.setAccessible(true);
//            mNonLocalizedDescriptionField.set(mServerInfo, description);
//            mNonLocalizedDescriptionField.setAccessible(false);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setCapability(int capability) {
//        try {
//            Class<? extends AccessibilityServiceInfo> aClass = mServerInfo.getClass();
//            Field mCapabilitiesField = aClass.getDeclaredField("mCapabilities");
//            mCapabilitiesField.setAccessible(true);
//            Integer mCapabilities = (Integer) mCapabilitiesField.get(mServerInfo);
//            if (mCapabilities != null) {
//                mCapabilitiesField.set(mServerInfo, mCapabilities |= capability);
//            }
//            Integer mCapabilities1 = (Integer) mCapabilitiesField.get(mServerInfo);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
    /*
     * windows内容变化是否监听
     */
//    public void setCanRetrieveWindowContent() {
//        setCapability(AccessibilityServiceInfo.CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT);
//    }
//
    /*
     * 是否可以截屏 api30支持
     */
//    public void setCanTakeScreenshot() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            setCapability(AccessibilityServiceInfo.CAPABILITY_CAN_TAKE_SCREENSHOT);
//        }
//    }
//
    /*
     * 设置支持手势 api24支持
     */
//    public void setCanGestures() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            setCapability(AccessibilityServiceInfo.CAPABILITY_CAN_PERFORM_GESTURES);
//        }
//    }
    public AccessibilityServiceInfo get() {
        return mServerInfo;
    }

}
