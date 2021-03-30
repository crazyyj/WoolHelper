package com.newchar.accesshelper;

import org.json.JSONObject;

/**
 * @author newChar
 * date 2021/3/29
 * @since 动作描述类
 * @since 迭代版本，（以及描述）
 */
public final class Action {

    /**
     * 动作标记类型
     * 1，ViewId
     * 2，Text
     * 3，坐标
     */
    int actionSignType = 0;

    /**
     * 当 @see actionSignType == 1 时，使用该变量定位要操作当View
     */
    String actionViewId = "";

    /**
     * 当 @see actionSignType == 2 时，使用该变量定位要操作当View
     */
    String actionViewText  = "";

    /**
     * 当 @see actionSignType == 3 时，使用该变量定位要操作当View
     * , 分割两个坐标点
     */
    String actionLocal  = "";   // 坐标点

    /**
     * 动作点唯一标示
     */
    String actionId = "";

    /**
     * 要触发是什么动作1， 点击， 2 长按，3 向上滑动，4 向下滑动，
     */
    String action = "";

    /**
     * Image的描述，用于配合定位控件，可以为null
     */
    String actionDesc  = "";

    /**
     * 动作所要操作的Node节点全类名，一般为View的类名
     */
    String actionView = "";

    /**
     * 动作执行次数 0 为无限次，正数为指定次数
     */
    int actionTimes = 1;

    /**
     * 要跟随执行的一组动作，其他动作的 actionId 值，
     * 要注意排序
     */
    String actionFollowUp = "";

    /**
     * 这个动作属于哪个页面
     */
    String actionPage = "";


    public static Action valueOf(JSONObject jsonObject) {
        Action action ;
        try {
            action = new Action();
            action.action = jsonObject.getString("action");
            action.actionId = jsonObject.getString("actionId");
            action.actionTimes = jsonObject.getInt("actionTimes");
            action.actionDesc = jsonObject.optString("actionDesc");
            action.actionPage = jsonObject.getString("actionPage");
            action.actionView = jsonObject.getString("actionView");
            action.actionLocal = jsonObject.getString("actionLocal");
            action.actionViewId = jsonObject.getString("actionViewId");
            action.actionSignType = jsonObject.getInt("actionSignType");
            action.actionViewText = jsonObject.getString("actionViewText");
            action.actionFollowUp = jsonObject.getString("actionFollowUp");
        } catch (Exception e ) {
            action = null;
            e.printStackTrace();
        }
        return action;
    }

    interface ActionEvent {

        /**
         * 点击
         */
        String CLICK = "1";

        /**
         * 长按
         */
        String CLICK_LONG = "2";

        /**
         * 横向滑动, 左
         */
        String SCROLL_X_UP = "3";

        /**
         * 横向滑动, 右
         */
        String SCROLL_X_DOWN = "4";

        /**
         * 竖向滑动，上
         */
        String SCROLL_Y_UP = "5";

        /**
         * 竖向滑动，下
         */
        String SCROLL_Y_DOWN = "6";

        /**
         * 选中
         */
        String SELECT = "7";

    }

    /**
     * 根据什么寻找锚点
     */
    interface ActionSign {

        /**
         * type 是 ViewID 查找
         */
        int BY_VIEW_ID = 1;

        /**
         * type 是 文本 查找
         */
        int BY_TEXT = 2;

        /**
         * type 是坐标点
         */
        int BY_POINT = 3;

    }

}