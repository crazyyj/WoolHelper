package com.newchar.woolhelper

import android.view.accessibility.AccessibilityEvent
import com.newchar.accesshelper.BaseAccessibilityService

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class AliPayService : BaseAccessibilityService() {
    //Timeline，ActivityTrigger，ActivityManager
    private lateinit var accessManager: AccessManager

    override fun onInterrupt() {
        accessManager.onDestroy()
        stopSelf()
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        accessManager.dispatchEvent(event)
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        accessManager = AccessManager(this)
    }

}