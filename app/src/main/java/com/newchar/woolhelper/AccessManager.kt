package com.newchar.woolhelper

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.newchar.accesshelper.BaseAccess
import com.newchar.woolhelper.service.wechat.Temp

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
        serverList.add(Temp())
    }

    fun dispatchEvent(event: AccessibilityEvent?): Boolean {
        if (event == null) {
            return false
        }
        for (access in serverList) {
            if (access.isShouldHandleEvent(event).and(access.handleEvent(service, event))) {
                return true
            }
        }

        return false
    }

    fun onDestroy() {
//        for (access in serverList) {
//        }
    }


}