package com.newchar.woolhelper.replacer

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-30
 * @since 状态改变监听器，
 */
interface StateChangeListener {
    /**
     * 状态切换时，回调
     *
     * @param oldState 老状态
     * @param newState 新状态
     */
    fun onStateChanged(oldState: Int, newState: Int)
}