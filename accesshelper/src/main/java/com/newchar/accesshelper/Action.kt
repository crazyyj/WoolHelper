package com.newchar.accesshelper

import org.json.JSONObject
import java.lang.Exception

/**
 *  @author newChar
 *  date 2021/3/16
 *  @since 动作实体类
 *  @since 迭代版本，（以及描述）
 */
class Action {

    /**
     * 动作标记类型
     * 1，ViewId
     * 2，Text
     * 3，坐标
     */
    var actionSignType: Int = 0

    /**
     * 当 @see actionSignType == 1 时，使用该变量定位要操作当View
     */
    var actionViewId: String = ""

    /**
     * 当 @see actionSignType == 2 时，使用该变量定位要操作当View
     */
    var actionViewText: String = ""

    /**
     * 当 @see actionSignType == 3 时，使用该变量定位要操作当View
     * , 分割两个坐标点
     */
    var actionLocal: String = ""   // 坐标点

    /**
     * 动作点唯一标示
     */
    var actionId: String = ""

    /**
     * 要触发是什么动作1， 点击， 2 长按，3 向上滑动，4 向下滑动，
     */
    var action: String = ""

    /**
     * Image的描述，用于配合定位控件，可以为null
     */
    var actionDesc: String = ""

    /**
     * 动作所要操作的Node节点全类名，一般为View的类名
     */
    var actionView: String = ""

    /**
     * 动作执行次数 0 为无限次，正数为指定次数
     */
    var actionTimes: Int = 1

    /**
     * 要跟随执行的一组动作，其他动作的 actionId 值，
     * 要注意排序
     */
    var actionFollowUp: String = ""

    /**
     * 这个动作属于哪个页面
     */
    var actionPage: String = ""


    companion object {

        fun valueOf(jsonObject: JSONObject): Action? {
            var action: Action?
            try {
                action = Action()
                action.action = jsonObject.getString("action")
                action.actionId = jsonObject.getString("actionId")
                action.actionTimes = jsonObject.getInt("actionTimes")
                action.actionDesc = jsonObject.optString("actionDesc")
                action.actionPage = jsonObject.getString("actionPage")
                action.actionView = jsonObject.getString("actionView")
                action.actionLocal = jsonObject.getString("actionLocal")
                action.actionViewId = jsonObject.getString("actionViewId")
                action.actionSignType = jsonObject.getInt("actionSignType")
                action.actionViewText = jsonObject.getString("actionViewText")
                action.actionFollowUp = jsonObject.getString("actionFollowUp")
            } catch (e: Exception) {
                action = null
                e.printStackTrace()
            }
            return action
        }

    }

    object ActionEvent {

        /**
         * 点击
         */
        val CLICK = "1"

        /**
         * 长按
         */
        val CLICK_LONG = "2"

        /**
         * 横向滑动, 左
         */
        val SCROLL_X_UP = "3"

        /**
         * 横向滑动, 右
         */
        val SCROLL_X_DOWN = "4"

        /**
         * 竖向滑动，上
         */
        val SCROLL_Y_UP = "5"

        /**
         * 竖向滑动，下
         */
        val SCROLL_Y_DOWN = "6"

        /**
         * 选中
         */
        val SELECT = "7"



    }

    /**
     * 根据什么寻找锚点
     */
    object ActionSign {

        /**
         *  type 是 ViewID 查找
         */
        val BY_VIEW_ID: Int = 1

        /**
         * type 是 文本 查找
         */
        val BY_TEXT: Int = 2

        /**
         * type 是坐标点
         */
        val BY_POINT: Int = 3

    }

}