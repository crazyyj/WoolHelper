package com.newchar.woolhelper.service.dingtalk

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.newchar.accesshelper.BaseAccess
import com.newchar.woolhelper.service.ClassName
import com.newchar.woolhelper.service.PackageName
import org.w3c.dom.Node
import org.w3c.dom.Text

/**
 *  @author wenliqiang
 *  date 2019-11-13
 *  @since 当前版本，（以及描述）
 *  @since 迭代版本，（以及描述）
 */
class DingTalkPunch : BaseAccess {

    override fun getPackName(): String = PackageName.DING_TALK

    override fun getClassName(): String = ClassName.DING_TALK_PUNCH

    override fun isShouldHandleEvent(event: AccessibilityEvent): Boolean {
        return TextUtils.equals(getPackName(), event.packageName) && TextUtils.equals(getClassName(), event.className)
    }

    override fun getPriority(): Int = 0

    override fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean {
        val rootNode = service.rootInActiveWindow ?: return false
        val relatlayout = rootNode.findAccessibilityNodeInfosByViewId("com.alibaba.android.rimet:id/webview_frame")
        if (!relatlayout.isNullOrEmpty()) {
            val webViewNode = relatlayout.get(0).getChild(0).getChild(0).getChild(0)
            val ListViewNode = webViewNode.getChild(1).getChild(1)
            val punchParent = ListViewNode.getChild(0).getChild(0) ?: return false
            for (index in 1 until punchParent.childCount) {
                if (punchParent.getChild(index).isClickable) {
                    punchParent.getChild(index).performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    return true
                }
            }
        }
        return false
    }
}