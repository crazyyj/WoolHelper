package com.newchar.accesshelper

import android.view.accessibility.AccessibilityNodeInfo

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class Utils {

    companion object{

        fun recycle(nodeInfo: AccessibilityNodeInfo) {
            nodeInfo.recycle()
        }

    }

}