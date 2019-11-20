package com.newchar.accesshelper

import android.content.Context
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import org.jetbrains.annotations.NotNull

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class Utils {

    companion object {

        /**
         * 回收Node对象
         */
        fun recycle(nodeInfo: AccessibilityNodeInfo?) {
            nodeInfo?.recycle()
        }

        /**
         * 是否开启服务
         */
        fun isOpenService(@NotNull context: Context): Boolean {
            val manager =
                context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
            return manager.isEnabled
        }


    }

}