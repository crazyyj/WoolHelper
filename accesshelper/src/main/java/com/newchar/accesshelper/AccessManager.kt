package com.newchar.accesshelper

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Process
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import java.io.File

/**
 *  @author         newChar
 *  date            2019-11-15
 *  @version
 *  @since          入口，
 *  @since          迭代版本描述
 */
class AccessManager(val context: Context) {

    private val INIT_LOAD: Int = 1
    private val INIT_ACCESS_INFO: Int = 2

    private var mHandler: Handler
    private var serviceInfoListener: ServiceInfoChangeListener? = null

    private val eventCallBack = Handler.Callback {
        when (INIT_LOAD) {
            INIT_LOAD -> loadContent()
            INIT_ACCESS_INFO -> parsePkgInfo()
        }
        true
    }

    private fun parsePkgInfo() {
        val actionManager = ActionManager.getInstance()
        val allEnablePackageName = actionManager.getPackageNameIfEnable()
        val build = ServiceInfoCompat.Builder(AccessibilityServiceInfo())
            .addPackages(allEnablePackageName)
            .capability(1)
            .build()
        serviceInfoListener?.onServiceInfoChange(build)
    }


    init {

        val tempThread = HandlerThread(
            "AccessManager InitThread",
            Process.THREAD_PRIORITY_BACKGROUND
        )
        tempThread.start()
        mHandler = Handler(tempThread.looper, eventCallBack)
    }

    fun init() {
        //加载本地环境
        mHandler.sendEmptyMessage(INIT_LOAD)

        mHandler.sendEmptyMessage(INIT_ACCESS_INFO)
    }

    fun setServiceInfoChangeListener(info:ServiceInfoChangeListener) {
        AccessManager@this.serviceInfoListener = info
    }

    public interface ServiceInfoChangeListener {
        fun onServiceInfoChange(info: AccessibilityServiceInfo)
    }

    fun release() {
        mHandler.looper.quitSafely()
        mHandler.removeCallbacksAndMessages(null)

        serviceInfoListener = null
    }

    private fun loadContent() {
        val ruleFile = File(context.getExternalFilesDir(""), "access.json")
        val ruleFileJson = IOUtils.readText2String(ruleFile)
        val actionManager = ActionManager.getInstance()
        actionManager.loadActions(ruleFileJson)
    }

    fun onAccessibilityEvent(service: AccessibilityService, event: AccessibilityEvent) {
        val actionManager = ActionManager.getInstance()
        if (actionManager.searchPageMatchForEvent(event)) {
            actionManager.execute(service, event)


        }
    }

}