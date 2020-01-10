package com.newchar.woolhelper.replacer

import android.R
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.appcompat.widget.LinearLayoutCompat

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-21
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
class ActivityAttach(override val host: Activity?) : BaseStateLayout<Activity?>() {
    private var mUserSetContainer: ViewGroup? = null
    @IdRes
    private val activityRootId = R.id.content

    /**
     * 如果你要更改 父布局，
     *
     * @param replaceContainer 切换 状态View 的容器
     */
    fun replaceContainer(replaceContainer: ViewGroup?) {
        mUserSetContainer = replaceContainer
    }

    fun switchState(state: Int) {
        val stateLayout = layoutArrays[state] ?: return
        val rootView: ViewGroup?
        rootView = if (mUserSetContainer == null) {
            val rootViewContainer = host!!.findViewById<ViewGroup>(activityRootId)
            if (rootViewContainer.getChildAt(0) is ViewGroup) {
                rootViewContainer.getChildAt(0) as ViewGroup
            } else {
                rootViewContainer
            }
        } else {
            mUserSetContainer
        }
        if (rootView == null) {
            return
        }
        //把这个View 缓存起来。 从父 View 剔除
        val s =
            LayoutInflater.from(host).inflate(stateLayout.layoutId, rootView)
        stateLayout.convert(s)
        //---end
        if (rootView is LinearLayout || rootView is LinearLayoutCompat) { //如果是线性布局，则加在第一个位置。
            rootView.addView(
                s,
                0,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        } else { //其他的布局加载最后的位置
            rootView.addView(s)
        }
        this.state = state
    }

}