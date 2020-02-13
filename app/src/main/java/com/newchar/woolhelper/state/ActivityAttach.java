package com.newchar.woolhelper.state;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-21
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
public class ActivityAttach extends BaseStateLayout<Activity> {

    private ViewGroup mUserSetContainer;

    @IdRes
    private final int activityRootId = android.R.id.content;

    /**
     * 如果你要更改 父布局，
     *
     * @param replaceContainer 切换 状态View 的容器
     */
    public void replaceContainer(ViewGroup replaceContainer) {
        mUserSetContainer = replaceContainer;
    }

    public void switchState(int state) {
        StateLayout stateLayout = layoutArrays.get(state);
        if (stateLayout == null) {
            return;
        }

        ViewGroup rootView;
        if (mUserSetContainer == null) {
            ViewGroup rootViewContainer = host.findViewById(activityRootId);
            if (rootViewContainer.getChildAt(0) instanceof ViewGroup) {
                rootView = (ViewGroup) rootViewContainer.getChildAt(0);
            } else {
                rootView = rootViewContainer;
            }
        } else {
            rootView = mUserSetContainer;
        }
        if (rootView == null) {
            return;
        }
        //把这个View 缓存起来。 从父 View 剔除
        View s = LayoutInflater.from(host).inflate(stateLayout.getLayoutId(), rootView);
        stateLayout.convert(s);
        //---end
        if (rootView instanceof LinearLayout || rootView instanceof LinearLayoutCompat) {
            //如果是线性布局，则加在第一个位置。
            rootView.addView(s, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            //其他的布局加载最后的位置
            rootView.addView(s);
        }
        this.state = state;
    }




}
