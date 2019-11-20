package com.newchar.accesshelper

import android.view.accessibility.AccessibilityNodeInfo

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-20
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class NodeActionCompat(private val nodeInfo: AccessibilityNodeInfo?) {

    /**
     * 回收Node对象
     */
    fun recycle() {
        nodeInfo?.recycle()
    }

    /**
     * 向下滚动
     */
    fun scrollDown(nodeInfo: AccessibilityNodeInfo?): Boolean {
        var result = false
        if (nodeInfo?.isScrollable!!) {
            result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD)
        }
        return result
    }

    /**
     * 向上
     */
    fun scrollUp(nodeInfo: AccessibilityNodeInfo?): Boolean? {
        return nodeInfo?.run {
            if (isScrollable) {
                performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
            } else false
        }
    }

    /**
     * 根据id查找Node对象
     */
    fun findOneNodeByViewId(id: String): AccessibilityNodeInfo? {
        val nodeInfos = nodeInfo?.findAccessibilityNodeInfosByViewId(id)
        return nodeInfos?.firstOrNull()
    }

    /**
     * 根据 Text 或者 Desc 查找Node对象
     */
    fun findOneNodeByText(text: String): AccessibilityNodeInfo? {
        val nodeInfos = nodeInfo?.findAccessibilityNodeInfosByText(text)
        return nodeInfos?.firstOrNull()
    }
}