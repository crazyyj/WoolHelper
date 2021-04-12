package com.newchar.accesshelper.compat;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * @author newChar
 * date 2021/3/27
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class ActionInfoCompat {


    /**
     * 向后滚动
     */
    public static boolean scrollDown(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo && nodeInfo.isScrollable()) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
        }
        return result;
    }

    /**
     * 长按事件
     */
    public static boolean performLongClick(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo && nodeInfo.isClickable()) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
        }
        return result;
    }

    /**
     * 点击事件
     */
    public static boolean performClick(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo && nodeInfo.isClickable()) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
        return result;
    }

    /**
     * 向前滚动
     */
    public static boolean scrollUp(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo && nodeInfo.isScrollable()) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        }
        return result;
    }

    public static boolean performGoBack(AccessibilityService service) {
        return performGlobalAction(service, AccessibilityService.GLOBAL_ACTION_BACK);
    }

    /**
     * 按下Home键
     *
     * @param service 服务
     * @return 是否成功
     */
    public static boolean performHome(AccessibilityService service) {
        return performGlobalAction(service, AccessibilityService.GLOBAL_ACTION_HOME);
    }

    /**
     * @param service 操作的Service对象
     * @return 是否成功
     */
    public static boolean performRecent(AccessibilityService service) {
        return performGlobalAction(service, AccessibilityService.GLOBAL_ACTION_RECENTS);
    }

    public static boolean performNotifications(AccessibilityService service) {
        return performGlobalAction(service, AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS);
    }

    public static boolean performLockScreen(AccessibilityService service) {
        if (Build.VERSION_CODES.P <= Build.VERSION.SDK_INT) {
            return performGlobalAction(service, AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN);
        }
        return false;
    }

    private static boolean performGlobalAction(AccessibilityService service, int globalAction) {
        boolean result = false;
        if (null != service) {
            result = service.performGlobalAction(globalAction);
        }
        return result;
    }


}
