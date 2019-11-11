package com.newchar.accesshelper

import android.accessibilityservice.AccessibilityServiceInfo

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class ServiceConfig {

    val serviceInfo: AccessibilityServiceInfo = AccessibilityServiceInfo()

    public class Build(val serviceInfo: AccessibilityServiceInfo) {


        fun eventType(type: Int) {
//            serviceInfo.eventTypes |= type
        }


    }


}