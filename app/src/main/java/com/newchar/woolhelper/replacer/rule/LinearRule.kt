package com.newchar.woolhelper.replacer.rule

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat

/**
 * @author wenliqiang@100tal.com
 * date            2020-01-03
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
class LinearRule : IRule {
    override fun isRule(view: View?): Boolean {
        return view is LinearLayoutCompat || view is LinearLayout
    }
}