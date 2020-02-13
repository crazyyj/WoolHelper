package com.newchar.woolhelper.replacer

import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.annotation.IdRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.collection.SparseArrayCompat
import androidx.core.widget.NestedScrollView
import com.newchar.woolhelper.R

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-21
 * @since 切换框架入口
 * @since 迭代版本描述
 */
class StateLayout {
    private val viewContainer = SparseArrayCompat<View>()
    private var stateView: View? = null
    /**
     * 原容器内View
     */
    private var successView: View? = null

    fun convert(view: View?) {
        stateView = view
    }


    val layoutId: Int
        get() = R.layout.activity_main

    /**
     * 不删除，只隐藏
     */
    fun hide() {

    }

    /**
     * 替换指定到View位置，
     */
    fun replace(view: View) {

    }

    /**
     * 替换指定到View位置，
     */
    fun replace(id: Int, view: View) {

    }

//    /**
//     * 替换指定到View位置，
//     */
//    fun replace(index: Int, view: View) {
//
//    }

    fun add(view: View) {

    }

    /**
     * 显示一个View，如果没有添加到容器上，先add 后 显示
     */
    fun show(view: View) {

    }

    /**
     * 删除一个View
     */
    fun remove(view: View) {

    }

    /**
     * 替换内容模式
     *
     * @param contentView， 被替换内容
     */
    fun attachHost(contentView: View) {
        val _parent = contentView.parent
        if (_parent is ViewGroup) { //            int childIndex = ((ViewGroup) _parent).indexOfChild(contentView);
//            ((ViewGroup) _parent).addView(stateView, childIndex, updateViewParams(contentView));
//            contentView.setVisibility(View.GONE);
            attachCHost(_parent)
        }
    }

    /**
     * 容器填充模式
     *
     * @param containerView 父布局
     */
    fun attachCHost(containerView: ViewGroup?) {
        if (containerView == null) {
            return
        }
        // 滚动View
        if (containerView is ScrollView || containerView is HorizontalScrollView || containerView is NestedScrollView) {
            val childCount = containerView.childCount
            if (childCount > 0) { // 有子View
                successView = containerView.getChildAt(0)
                containerView.removeAllViews()
            }
            containerView.addView(
                stateView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        } else if (containerView is LinearLayout || containerView is LinearLayoutCompat) { // 线性布局，第一个位置，把其他都挤出去
            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            containerView.addView(stateView, 0, layoutParams)
        } else {
            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            val childCount = containerView.childCount
            val statePosition: Int
            statePosition = if (childCount > 0) {
                childCount - 1
            } else {
                0
            }
            containerView.addView(stateView, statePosition, layoutParams)
        }
    }

    /**
     * 用于兼容未知 ViewGroup 的模式， 比如 约束布局，其他自定义 ViewGroup
     *
     * @param contentView
     * @return
     */
    fun updateViewParams(contentView: View): ViewGroup.LayoutParams {
        return contentView.layoutParams
    }

    /**
     * 回去状态
     */
    fun reset() {}

    fun setEventListener(@IdRes viewId: Int, clickListener: View.OnClickListener?) {
        val view = viewContainer[viewId]
        if (view == null) {
            viewContainer.put(viewId, stateView!!.findViewById(viewId))
        }
        val eventView = viewContainer[viewId]
        if (eventView == null) {
            return
        }
        eventView.setOnClickListener(clickListener)
    }
}