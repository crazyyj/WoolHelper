package com.newchar.woolhelper

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.view.accessibility.AccessibilityEvent

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class AliPayService : AccessibilityService() {
//Timeline，ActivityTrigger，ActivityManager
    override fun onInterrupt() {


    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        if (TextUtils.equals("com.eg.android.AlipayGphone", event?.packageName)) {

        }

    }

    override fun onServiceConnected() {
        super.onServiceConnected()

    }

}