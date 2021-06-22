package com.newchar.accesshelper.compat;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Collections;
import java.util.List;

/**
 * @author newChar
 * date 2021/3/27
 * @since NodeInfo 版本兼容类
 * @since 迭代版本，（以及描述）
 */
public final class NodeInfoCompat {

    private NodeInfoCompat() {
    }

    /**
     * 根据id查找Node 列表对象
     */
    public static List<AccessibilityNodeInfo> findNodeByViewId(AccessibilityNodeInfo nodeInfo, String viewID){
        if (null != nodeInfo) {
            return nodeInfo.findAccessibilityNodeInfosByViewId(viewID);
        }
        return Collections.emptyList();
    }

    /**
     * 根据 Text 或者 Desc 查找Node对象
     */
    public static List<AccessibilityNodeInfo> findNodeByText(AccessibilityNodeInfo nodeInfo, String text){
        if (null != nodeInfo) {
            return nodeInfo.findAccessibilityNodeInfosByText(text);
        }
        return Collections.emptyList();
    }

    /**
     * 回收NodeInfo
     *
     * @param nodeInfo 被操作的Node
     */
    public static void recycle(AccessibilityNodeInfo nodeInfo) {
        if (null != nodeInfo) {
            nodeInfo.recycle();
        }
    }

}
