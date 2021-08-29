package com.newchar.accesshelper;

import com.newchar.accesshelper.log.LLL;

import java.util.ArrayList;
import java.util.List;

/**
 * @author newChar
 * date 2021/4/19
 * @since 动作组，
 * @since 迭代版本，（以及描述）
 */
class ActionGroup {


    /**
     * 事件组Id，用于识别是哪一个Group，一次性UUID自动生成
     */
    private String groupId;

    /**
     * 事件组名称
     */
    private String groupName;

    /**
     * 事件组别名，主要用于外界UI显示，
     */
    private String groupAliasName;

    /**
     * 事件组描述，
     */
    private String groupDesc;

    /**
     * 组状态
     */
    private int state = State.DEFAULT;

    /*
     * 组优先级
     */
//    private int priority = 0;

    /**
     * 正在执行的
     */
    public int index;

    private final List<Action> groupActions = new ArrayList<>();

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupAliasName(String groupAliasName) {
        this.groupAliasName = groupAliasName;
    }

    public String getGroupAliasName() {
        return groupAliasName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public void addAction(Action action) {
        groupActions.add(action);
    }

    /**
     * 获取下一个Action
     *
     * @return Action
     */
    public Action getAction() {
        int actionSize = groupActions.size();
        if (index < 0 || index >= actionSize) {
            LLL.w("ActionError", "getAction index = " + index + " actionSize = " + actionSize);
            return null;
        }
        return groupActions.get(index);
    }

    /**
     * 获取Action根据actionId
     *
     * @param actionId Action Id
     * @return Action
     */
    public Action getAction(String actionId) {
        int actionSize = groupActions.size();
        if (index < 0 || index >= actionSize) {
            LLL.w("ActionError", "getAction index = " + index + " actionSize = " + actionSize);
            return null;
        }
        return groupActions.get(index);
    }

    public void removeAction(Action action) {
        groupActions.remove(action);
    }

    public void clear() {
        groupActions.clear();
    }

    public void setState(int state) {
        this.state = state;
    }

    public void disable() {
        setState(State.DISABLE);
    }

    /**
     * 组状态
     */
    static final class State {

        /**
         * 不可用(例如有元素但是不执行该组Action)
         */
        static int DISABLE = -1;

        /**
         * 默认：按照顺序执行Action
         */
        static int DEFAULT = 0;

        /**
         * 无顺序，遇见哪个执行哪个
         */
        static int UNORDERED = 0;

    }

}
