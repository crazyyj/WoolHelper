package com.newchar.woolhelper.entry

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityEvent.TYPE_VIEW_CLICKED
import android.view.accessibility.AccessibilityNodeInfo
import com.newchar.accesshelper.BaseAccess

/**
 *  @author wenliqiang
 *  date 2019-12-15
 *  @since 当前版本，（以及描述）
 *  @since 迭代版本，（以及描述）
 */
class ActionAccess(var entry: ActionEntry) : BaseAccess {


    override fun getClassName(): String? {
        return entry.pageName
    }

    override fun isShouldHandleEvent(event: AccessibilityEvent): Boolean {
        return getClassName() == event.className
    }

    override fun getPriority(): Int {
        return 1
    }

    override fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean {
        val rootNode = service.rootInActiveWindow
        var anchorNode: List<AccessibilityNodeInfo> = ArrayList()
        if (entry.actionType == 1) {
            anchorNode = rootNode.findAccessibilityNodeInfosByText(entry.anchorText)
        } else if (entry.actionType == 2) { //id 查找
            anchorNode = rootNode.findAccessibilityNodeInfosByViewId(entry.viewId)
        }
        if (entry.action == TYPE_VIEW_CLICKED) {
            return anchorNode.first().performAction(TYPE_VIEW_CLICKED)
        }
        return false
    }

    override fun getPackName(): String? {
        return entry.packageName
    }


}