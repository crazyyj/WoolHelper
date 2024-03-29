package com.newchar.accesshelper.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author newChar
 * date 2021/4/19
 * @since 日志
 * @since 迭代版本，（以及描述）
 */
public class LLL {

    public static void e(String tag, String message) {
        Logger.getLogger(tag).log(Level.SEVERE, message);
    }

    public static void w(String tag, String message) {
        Logger.getLogger(tag).log(Level.WARNING, message);
    }

    public static void i(String tag, String message) {
        Logger.getLogger(tag).log(Level.INFO, message);
    }

}
