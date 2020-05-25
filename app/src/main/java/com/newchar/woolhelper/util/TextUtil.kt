package com.newchar.woolhelper.util

import android.text.TextUtils
import android.widget.TextView

/**
 *  @author wenliqiang
 *  date 2019-12-15
 *  @since 文本功能处理类
 *  @since 迭代版本，（以及描述）
 */
class TextUtil {

    companion object {

        /**
         * 从 TextView 中获取文本
         */
        fun getText(textView: TextView?): String {
            return getTextWithTrim(textView, false)
        }

        /**
         * 从 TextView 中获取文本， 并去除左右空白
         */
        fun getTextWithTrim(textView: TextView?, trim: Boolean): String {
            return if (trim) textView?.text.toString().trim()
                else textView?.text.toString()
        }

    }


}