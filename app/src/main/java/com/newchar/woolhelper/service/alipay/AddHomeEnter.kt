package com.newchar.woolhelper.service.alipay

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.newchar.accesshelper.BaseAccess
import com.newchar.accesshelper.compat.ActionInfoCompat
import com.newchar.woolhelper.service.ClassName
import com.newchar.woolhelper.service.IDs
import com.newchar.woolhelper.service.PackageName

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-12
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class AddHomeEnter :BaseAccess{


    override fun getPackName(): String {
        return PackageName.ALIPAY_HOME
    }

    override fun getClassName(): String {
        return ClassName.ALIPAY_ADD_MANAGER
    }

    override fun isShouldHandleEvent(event: AccessibilityEvent): Boolean {
        return TextUtils.equals(getPackName(), event.packageName).and(TextUtils.equals(getClassName(), event.className))
    }

    override fun getPriority(): Int {
        return 0
    }

    override fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean {
        val rootNode = service.rootInActiveWindow
        val recyclerViewList = rootNode.findAccessibilityNodeInfosByViewId(IDs.ALIPAY_ADD_HOME_ENTER_RECYCLERVIEW_TOP_ID)
        if (!recyclerViewList.isNullOrEmpty()) {
            val recyclerView = recyclerViewList.get(0)

//            clickAddAction(recyclerView.getChild(3))
            recyclerView.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
            val antForest = recyclerView.findAccessibilityNodeInfosByText("蚂蚁森林")
            if (!antForest.isNullOrEmpty()) {
                val antForestNode = antForest.get(0)
                val parent = antForestNode.parent
                if (TextUtils.equals(parent.viewIdResourceName,"com.alipay.android.phone.wallet.homemarket:id/app_info_view")) {
                    println("shi zhge yisi ")
                }
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                ActionInfoCompat.recycle(antForestNode)
                return true
            }
        }
        ActionInfoCompat.recycle(rootNode)
        return false
    }

}