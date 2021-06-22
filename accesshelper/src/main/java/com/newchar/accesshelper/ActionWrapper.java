package com.newchar.accesshelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author newChar
 * date 2021/3/29
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */

public final class ActionWrapper {

    /**
     * 在哪个页面进行处理
     */
    private String pageName = "";

    /**
     * 应用包名
     */
    private String pkgName = "";

    /**
     * 组数据可用性
     */
    private boolean available = true;

    private List<Action> actions = new ArrayList<>();

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean updateAvailable(boolean able) {
        return available = able;
    }

}