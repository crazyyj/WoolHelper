package com.newchar.accesshelper

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
interface BaseAccess {

    fun getPackName(): String

    fun getClassName(): String

    /**
     * 是否是我处理当前事件
     */
    fun isShouldHandleEvent(event: AccessibilityEvent): Boolean

    fun getPriority(): Int

    /**
     * 处理该事件。
     * @return 是否成功的处理了事件
     */
    fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean

}