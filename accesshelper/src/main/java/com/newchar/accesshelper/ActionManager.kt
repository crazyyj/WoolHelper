package com.newchar.accesshelper

import org.json.JSONObject
import java.io.File
import java.lang.Exception

/**
 * @author newChar
 * date 2021/3/16
 * @since 动作管理类
 * @since 迭代版本，（以及描述）
 */

class ActionManager private constructor() {

    /**
     * 被管理的全部动作
     */
    private val actionMap = mutableMapOf<String, MutableList<ActionWrapper>>()

    companion object {

        private lateinit var instance: ActionManager

        @Synchronized
        fun getInstance(): ActionManager {
            if (instance == null) {
                instance = ActionManager()
            }
            return instance
        }

    }

    /**
     * 添加一个动作
    //     */
    fun addAction(pkg: String, action: ActionWrapper) {
        val pkgNameValue = actionMap.get(action.pkgName)
        if (pkgNameValue == null) {
            actionMap[action.pkgName] = mutableListOf(action)
        } else {
            pkgNameValue.add(action)
        }
    }
//
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
            actionW.pkgName = pkgName
            val actionJsonArray = actionJson.getJSONArray("actionList")
            val actionList = mutableListOf<Action>()
            for (index in 1 until actionJsonArray.length()) {
                val actionObj = actionJsonArray[index] as JSONObject
                val action = Action.valueOf(actionObj)
                action?.run {
                    actionList.add(this)
                }
            }
            actionW.actions.addAll(actionList)

            // 把这个action 添加进去
            addAction(pkgName, actionW)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getPackageNameIfEnable(): MutableSet<String> {
        return actionMap.keys
    }

}

