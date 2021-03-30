package com.newchar.accesshelper.compat;

import android.accessibilityservice.AccessibilityServiceInfo;
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
 * android:accessibilityFeedbackType="feedbackSpoken"
 * android:accessibilityFlags="flagRetrieveInteractiveWindows|flagRequestFilterKeyEvents"
 * android:canRequestFilterKeyEvents="true"
 * android:canRetrieveWindowContent="true"
 * android:description="@string/app_name"
 * android:notificationTimeout="100"
 * android:packageNames="com.xxx.xxx" />
 * @since 迭代版本，（以及描述）
 */
public class ServiceInfoCompat {

    private final AccessibilityServiceInfo mServerInfo;

    public ServiceInfoCompat() {
        mServerInfo = new AccessibilityServiceInfo();
    }

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
            mServerInfo.packageNames = Arrays.copyOf(mServerInfo.packageNames, mServerInfo.packageNames.length + 1);
        }
    }

    public void setEventType() {
        mServerInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
    }

    public void setFeedBack() {
        mServerInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
    }

    public void setTimeOut() {
        mServerInfo.notificationTimeout = 100L;
    }

    public void setCapability(int capability) {
        try {
            Class<? extends AccessibilityServiceInfo> aClass = mServerInfo.getClass();
            Field mCapabilitiesField = aClass.getDeclaredField("mCapabilities");
            mCapabilitiesField.setAccessible(true);
            Integer mCapabilities = (Integer) mCapabilitiesField.get(mServerInfo);
            if (mCapabilities != null) {
                mCapabilitiesField.set(mServerInfo, mCapabilities | capability);
            }
            mCapabilitiesField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
