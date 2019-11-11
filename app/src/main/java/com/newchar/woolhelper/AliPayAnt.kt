package com.newchar.woolhelper

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.newchar.accesshelper.BaseAccess

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          蚂蚁森林，收取能量事件
 *  @since          迭代版本描述
 */
class AliPayAnt : BaseAccess {

    override fun getClassName(): String {
        return "com.eg.android.AlipayGphone/eActuvu"
    }

    override fun getPriority(): Int {
        return 1
    }

    override fun isShouldHandleEvent(event: AccessibilityEvent): Boolean {
        return TextUtils.equals(getPackName(), event.packageName)
            .and(TextUtils.equals(getClassName(), event.className))
    }

    override fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean {

        return true
    }

    fun findBullueParentView(event: AccessibilityEvent): AccessibilityNodeInfo? {
        if (TextUtils.equals("android.View.Button", event.className)) {
            return event.source
        }
        return null
    }

    override fun getPackName(): String {
        return "com.eg.android.AlipayGphone"
    }


}