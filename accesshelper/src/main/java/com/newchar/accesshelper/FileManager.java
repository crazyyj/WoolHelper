package com.newchar.accesshelper;

import android.content.Context;

import java.io.File;

/**
 * @author newChar
 * date 2021/4/19
 * @since 脚本存储目录管理
 * @since 迭代版本，（以及描述）
 */
class FileManager {

    /**
     * 获取保存 配置json文件等跟目录
     *
     * @return 获取配置文件根目录
     */
    public static File getScriptRootDir(Context context) {
        return context.getExternalFilesDir("script");
    }



}
