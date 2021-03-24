package com.newchar.accesshelper

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import org.json.JSONObject
import java.lang.Exception

/**
 * @author newChar
 * date 2021/3/16
 * @since 动作管理类
 * @since 迭代版本，（以及描述）
 */

class ActionManager {

    /**
     * 被管理的全部动作
     */
    private val actionMap = mutableMapOf<String, MutableList<ActionWrapper>>()

    companion object {

        private var instance: ActionManager = ActionManager()

        @Synchronized
        fun getInstance(): ActionManager {
            return instance
        }

    }

    /**
     * 添加一个动作
    //     */
    fun addAction(action: ActionWrapper) {
        val pkgNameValue = actionMap.get(action.pageName)
        if (pkgNameValue == null) {
            actionMap[action.pageName] = mutableListOf(action)
        } else {
            pkgNameValue.add(action)
        }
    }

//    fun removeActionFromPkg(pkg: String) {
//        if (actionMap.containsKey(pkg)) {
//            actionMap.remove(pkg)
//        }
//    }

    fun loadActions(json: String) {
        try {
            val actionJson = JSONObject(json)
            val pkgName = actionJson.getString("pkgName")
            val actionW = ActionWrapper()
            actionW.pageName = pkgName
            val actionJsonArray = actionJson.getJSONArray("actionList")
            val actionList = mutableListOf<Action>()
            for (index in 0 until actionJsonArray.length()) {
                val actionObj = actionJsonArray[index] as JSONObject
                val action = Action.valueOf(actionObj)
                action?.run {
                    actionList.add(this)
                }
            }
            actionW.actions.addAll(actionList)

            // 把这个action 添加进去
            addAction(actionW)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getPackageNameIfEnable(): MutableSet<String> {
        return actionMap.keys
    }

    fun searchPageMatchForEvent(event: AccessibilityEvent?): Boolean {
        event?.let {
            if (getPackageNameIfEnable().contains(event.packageName)) {
                val actionList = actionMap[event.packageName]
                for (index in 1 until (actionList?.size ?: 0)) {
                    val actionWrapper = actionList?.get(index)
                    return TextUtils.equals(actionWrapper?.pageName, event.className)
                }
            }
        }
        return false
    }

    fun execute(service: AccessibilityService, event: AccessibilityEvent) {
        val actionList = actionMap[event.packageName]
        for (index in 1 until (actionList?.size ?: 0)) {
            val actionWrapper = actionList?.get(index)
            val action = actionWrapper?.actions
            for (actionIndex in 1 until (action?.size ?: 0)) {
                val actionGet = action?.get(actionIndex)
                actionGet?.run {
                    when (actionGet.actionSignType) {
                        Action.ActionSign.BY_POINT -> {

                        }
                        Action.ActionSign.BY_TEXT -> {
                            val findAccessibilityNodeInfosByText =
                                service.rootInActiveWindow.findAccessibilityNodeInfosByText(
                                    actionGet.actionViewText
                                )
                            if (actionGet.action == Action.ActionEvent.CLICK) {
                                findAccessibilityNodeInfosByText[0].performAction(
                                    AccessibilityNodeInfo.ACTION_CLICK
                                )
                            } else if (actionGet.action == Action.ActionEvent.CLICK_LONG) {
                                findAccessibilityNodeInfosByText[0].performAction(
                                    AccessibilityNodeInfo.ACTION_LONG_CLICK
                                )
                            } else {
                            }
                        }
                        Action.ActionSign.BY_VIEW_ID -> {
                            service.rootInActiveWindow.findAccessibilityNodeInfosByViewId(actionGet.actionViewId)
                        }
                        else -> Log.e("Action", " 现在的actionSignType " + actionGet.actionSignType)
                    }
                }
            }

        }
    }

}

