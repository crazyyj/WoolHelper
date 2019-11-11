package com.newchar.woolhelper

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class AccessManager(val service: AccessibilityService) {

    var serverList = ArrayList<BaseAccess>()

    init {
        serverList.add(AliPayAnt())
    }

    fun dispatchEvent(event: AccessibilityEvent?): Boolean {
        if (event == null) {
            return false
        }
        for (access in serverList) {
            access.isShouldHandleEvent(event).and(access.handleEvent(service, event))
        }

        return false
    }

}