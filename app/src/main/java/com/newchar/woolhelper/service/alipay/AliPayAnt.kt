package com.newchar.woolhelper.service.alipay

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import com.newchar.accesshelper.BaseAccess
import com.newchar.woolhelper.service.ClassName
import com.newchar.woolhelper.service.IDs
import com.newchar.woolhelper.service.PackageName

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          蚂蚁森林，收取能量事件
 *  @since          迭代版本描述
 */
class AliPayAnt : BaseAccess {

    override fun getPriority(): Int {
        return 1
    }

    override fun isShouldHandleEvent(event: AccessibilityEvent): Boolean {
        return TextUtils.equals(getPackName(), event.packageName).and(TextUtils.equals(getClassName(), event.className))
    }

    override fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean {
        val rootInActiveWindow = service.rootInActiveWindow
        val firends = rootInActiveWindow.findAccessibilityNodeInfosByViewId(IDs.ALIPAY_ANT_CONTAINER_ID)

        if (!firends.isNullOrEmpty()) {
            val container = firends.get(0)
            val scrollWebView = container.getChild(0).getChild(0).getChild(0)
            var friendsListView = scrollWebView.getChild(0).getChild(1)
            val moreFriends = container.findAccessibilityNodeInfosByText("查看更多好友")
            if (!moreFriends.isNullOrEmpty()) {
                moreFriends.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK)
            }
//            child.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
//            child.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
//            child.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
            return true
        }
        return false
    }

    fun findBullueParentView(event: AccessibilityEvent): AccessibilityNodeInfo? {
        if (TextUtils.equals("android.View.Button", event.className)) {
            return event.source
        }
        return null
    }

    override fun getPackName(): String {
        return PackageName.ALIPAY_HOME
    }

    override fun getClassName(): String {
        return ClassName.ALIPAY_ANT
    }

}