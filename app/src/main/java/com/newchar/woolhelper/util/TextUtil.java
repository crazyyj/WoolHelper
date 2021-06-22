package com.newchar.woolhelper.util;

import android.widget.TextView;

/**
 * @author newChar
 * date 2021/6/18
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public final class TextUtil {

    /**
     * 从 TextView 中获取文本
     */
    public static CharSequence getText(TextView textView) {
        return getTextWithTrim(textView, false);
    }

    /**
     * 从 TextView 中获取文本， 并去除左右空白
     */
    public static String getTextWithTrim(TextView textView, boolean trim) {
        String text = textView.getText().toString();
        if (trim) {
            text = text.trim();
        }
        return text;
    }


}
