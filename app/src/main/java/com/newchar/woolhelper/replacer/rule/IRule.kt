package com.newchar.woolhelper.replacer.rule

import android.view.View

/**
 * @author wenliqiang@100tal.com
 * date            2020-01-03
 * @since 判断覆盖条件的规则
 * @since 迭代版本描述
 */
interface IRule {
    /**
     * 是否符合被自己处理的条件
     *
     * @param container 装载状态 View 的容器
     * @return 是符合条件的 需要被处理
     */
    fun isRule(container: View?): Boolean
}