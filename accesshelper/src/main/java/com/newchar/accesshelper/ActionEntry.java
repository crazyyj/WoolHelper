package com.newchar.accesshelper;

/**
 * @author newChar
 * date 2021/6/18
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class ActionEntry {

    /**
     * 表中的自增
     */
    int _id = 0;

    /**
     * 事件id，（事件唯一值， 一定有值）
     */
    String id;

    /**
     * 事件的名称
     */
    String name;

    /**
     * 事件的描述
     */
    String desc;

    /**
     * 事件触发的页面
     */
    String pageName;

    /**
     * 事件触发的包名
     */
    String packageName;

    /**
     * 操作的事件
     * 点击，长按，滑动等。。。
     */
    int action;

    /**
     * 寻找的文本（锚点文本）
     */
    String anchorText;


    /**
     * 寻找的文本
     * 包名id/jdnfksdnkfj
     */
    String viewId;


    /**
     * 寻找事件的类型
     * 1. 文本
     * 2. ViewId
     * 3. 坐标（高级版功能）
     */
    int actionType;

    /**
     * 事件状态
     * 0.  事件可用（默认）
     * -1. 已删除，待完全删除
     * -2. 本地完全删除
     */
    int enableState;

    /**
     * 事件是否开启
     * 0. 未开启
     * 1. 已开启
     */
    int open;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getAnchorText() {
        return anchorText;
    }

    public void setAnchorText(String anchorText) {
        this.anchorText = anchorText;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getEnableState() {
        return enableState;
    }

    public void setEnableState(int enableState) {
        this.enableState = enableState;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }
}
