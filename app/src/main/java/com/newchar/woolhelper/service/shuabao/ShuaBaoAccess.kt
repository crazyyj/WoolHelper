package com.newchar.woolhelper.service.shuabao

import android.accessibilityservice.AccessibilityService
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.newchar.accesshelper.BaseAccess
import com.newchar.accesshelper.log.PrintNodeTree
import com.newchar.woolhelper.service.ClassName
import com.newchar.woolhelper.service.PackageName

/**
 *  @author         wenliqiang@100tal.com
 *  date            2020-01-08
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class ShuaBaoAccess : BaseAccess {

    private val mHandler: Handler = Handler(Handler.Callback {
        val obj = it.obj as AccessibilityNodeInfo
        obj.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
        return@Callback true
    })

    override fun getPackName(): String {
        return PackageName.SHUA_BAO_PACKAGE_NAME
    }

    override fun getClassName(): String {
        return ClassName.PAGE_SHUA_BAO_VIDEO_PLAY
    }

    override fun isShouldHandleEvent(event: AccessibilityEvent): Boolean {
        return TextUtils.equals(
            getPackName(),
            event.packageName
        ) && TextUtils.equals(getClassName(), event.className)
    }

    override fun getPriority(): Int {
        return 1
    }

    /**
     * com.jm.video:id/list
     */
    override fun handleEvent(service: AccessibilityService, event: AccessibilityEvent): Boolean {
        val rootNode = service.rootInActiveWindow ?: return false
        PrintNodeTree.print(rootNode)

        val firstOrNull = rootNode.findAccessibilityNodeInfosByViewId("com.jm.video:id/mmViewPager").firstOrNull()
        firstOrNull?.apply {
            sendMessage(firstOrNull)
            return true
        }
        return false
    }

    private fun sendMessage(info: AccessibilityNodeInfo) {
        mHandler.sendMessageDelayed(mHandler.obtainMessage(1, info), 5000L)
    }
}