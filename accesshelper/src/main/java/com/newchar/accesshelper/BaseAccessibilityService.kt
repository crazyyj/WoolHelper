package com.newchar.accesshelper

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

/**
 *  @author
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
open class BaseAccessibilityService : AccessibilityService() {

    private lateinit var accessManager: AccessManager

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.e("ccessibilityService", "qidongle")

        accessManager = AccessManager(this)
        accessManager.init()
        accessManager.setServiceInfoChangeListener(object :
            AccessManager.ServiceInfoChangeListener {
            override fun onServiceInfoChange(info: AccessibilityServiceInfo) {
                serviceInfo = info
            }
        })
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.run {
//            把Event传进去，根据包名，类名过滤然后执行后边的具体判断罗技。要执行哪些操作
            accessManager.onAccessibilityEvent(this@BaseAccessibilityService, event)
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onInterrupt() {
        accessManager?.release()
    }

}