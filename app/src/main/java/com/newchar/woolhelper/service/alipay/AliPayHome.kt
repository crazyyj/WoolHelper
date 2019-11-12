package com.newchar.woolhelper.service.alipay

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.newchar.accesshelper.BaseAccess
import com.newchar.accesshelper.Utils
import com.newchar.woolhelper.service.ClassName
import com.newchar.woolhelper.service.PackageName

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-12
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class AliPayHome : BaseAccess {


    override fun getPackName(): String {
        return PackageName.ALIPAY_HOME
    }

    override fun getClassName(): String {
        return ClassName.ALIPAY_HOME
    }

    override fun isShouldHandleEvent(event: AccessibilityEvent): Boolean {
        return TextUtils.equals(
            getPackName(),
            event.packageName
        ) && TextUtils.equals(getClassName(), event.className)
    }

    override fun getPriority(): Int {
        return 2
    }

    override fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean {
        val rootNode = service.rootInActiveWindow
        val more = rootNode.findAccessibilityNodeInfosByText("更多")
        for (moreNode in more) {
            if (TextUtils.equals(moreNode.className, "android.widget.TextView")) {
                moreNode.parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            }
        }
//        val recycleNode = rootNode.findAccessibilityNodeInfosByViewId(IDs.ALIPAY_HOME_RECYCLERVIEW_TOP_ID)
//        if (recycleNode != null && recycleNode.isNotEmpty()) {
//            val recycleView = recycleNode.get(0)
//            val tvAntForest = recycleView.findAccessibilityNodeInfosByText("蚂蚁森林")
//            if (tvAntForest.isNullOrEmpty()) {
//                //点击更多
//                val more = recycleView.findAccessibilityNodeInfosByText("更多")
//                if (!more.isNullOrEmpty()) {
//                    more.get(0).parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
//                }
//            }
//        }
        Utils.recycle(rootNode)
        return false
    }
}