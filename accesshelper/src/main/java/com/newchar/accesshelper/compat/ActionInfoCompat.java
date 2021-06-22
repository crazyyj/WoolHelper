package com.newchar.accesshelper.compat;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;

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
     * 复制事件
     */
    public static boolean performCopy(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_COPY);
        }
        return result;
    }

    /**
     * 剪切事件
     */
    public static boolean performCut(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CUT);
        }
        return result;
    }

    /**
     * 粘贴事件
     */
    public static boolean performPaste(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_PASTE);
        }
        return result;
    }

    /**
     * 设置文字事件
     */
    public static boolean performSetText(AccessibilityNodeInfo nodeInfo, String text) {
        boolean result = false;
        if (null != nodeInfo) {
            Bundle arguments = new Bundle();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
                result = nodeInfo.performAction(AccessibilityAction.ACTION_SET_TEXT.getId(), arguments);
            }
        }
        return result;
    }

    /**
     * 选择文本, 一般与复制粘贴一起使用
     */
    public static boolean performSelectText(AccessibilityNodeInfo nodeInfo, int selectionStart, int selectionEnd) {
        boolean result = false;
        if (null != nodeInfo) {
            Bundle arguments = new Bundle();
            arguments.putInt(AccessibilityNodeInfo.ACTION_ARGUMENT_SELECTION_START_INT, selectionStart);
            arguments.putInt(AccessibilityNodeInfo.ACTION_ARGUMENT_SELECTION_END_INT, selectionEnd);
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_SELECTION, arguments);
        }
        return result;
    }

    /**
     * 清除选中文本状态
     */
    public static boolean performClearSelectText(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLEAR_SELECTION);
        }
        return result;
    }

    /**
     * 选中状态
     */
    public static boolean performSelect(AccessibilityNodeInfo nodeInfo) {
        boolean result = false;
        if (null != nodeInfo) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SELECT);
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

    /**
     * @param nodeInfo 需要执行的节点
     * @param times    需要向上找几次
     * @return 是否执行成功
     */
    public static boolean forcePerformClick(AccessibilityNodeInfo nodeInfo, int times) {
        boolean result = performClick(nodeInfo);
        if (!result && times > 0) {
            return forcePerformClick(nodeInfo.getParent(), --times);
        }
        return result;
    }

}
