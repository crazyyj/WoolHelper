package com.newchar.accesshelper

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class BaseAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
    }

    override fun onInterrupt() {

    }

}