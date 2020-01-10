package com.newchar.woolhelper.replacer

import androidx.collection.SparseArrayCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-21
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
abstract class BaseStateLayout<T> : IStateLayout<T?> {

    var hostAA : T? = TODO()

    /**
     * 当前处于那种 state
     */
    var state = 0

    protected var layoutArrays = SparseArrayCompat<StateLayout>(4)
    protected var mStateChangeListener: OnPageChangeListener? = null

    override fun attach(attach: T?) {
        hostAA = attach
    }

    fun putLayout(state: Int, stateLayout: StateLayout) {
        layoutArrays.put(state, stateLayout)
    }

    fun getLayout(state: Int): StateLayout? {
        return layoutArrays[state]
    }

}