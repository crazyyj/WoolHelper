package com.newchar.woolhelper

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.newchar.accesshelper.BaseAccess
import com.newchar.woolhelper.service.alipay.AddHomeEnter
import com.newchar.woolhelper.service.alipay.AliPayAnt
import com.newchar.woolhelper.service.alipay.AliPayHome
import com.newchar.woolhelper.service.dingtalk.DingTalkPunch
import com.newchar.woolhelper.service.shuabao.ShuaBaoAccess
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
//        serverList.add(AliPayHome())
//        serverList.add(AddHomeEnter())
//        serverList.add(AliPayAnt())
//        serverList.add(DingTalkPunch())
        serverList.add(ShuaBaoAccess())
    }

    fun dispatchEvent(event: AccessibilityEvent?): Boolean {
        if (event == null) {
            return false
        }
        for (access in serverList) {
            if (access.isShouldHandleEvent(event) && access.handleEvent(service, event)) {
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