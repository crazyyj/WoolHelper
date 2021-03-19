package com.newchar.woolhelper

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.newchar.accesshelper.BaseAccess

/**
 * @author newChar
 * date 2021/3/14
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
 class ZhiHuSearchEvent :BaseAccess{

    override fun getPackName(): String {
        return "com.zhihu.android"
    }

    override fun getClassName(): String {
        return "com.zhihu.android.app.ui.activity.MainActivity"
    }

    override fun isShouldHandleEvent(event: AccessibilityEvent): Boolean {
        return TextUtils.equals(getPackName(), event.packageName).and(TextUtils.equals(getClassName(), event.className))
    }

    override fun getPriority(): Int {
        return 0

    }

    override fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean {
        var rootInActiveWindow = service.rootInActiveWindow
        var searchLayout =
            rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.zhihu.android:id/input_root")
        searchLayout.firstOrNull()?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        return true
    }

}