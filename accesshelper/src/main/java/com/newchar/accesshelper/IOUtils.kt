package com.newchar.accesshelper

import java.io.File

/**
 *  @author newChar
 *  date 2021/3/17
 *  @since 当前版本，（以及描述）
 *  @since 迭代版本，（以及描述）
 */
object IOUtils {

    fun readText2String(textFile: File): String {
        try {
            return textFile.readText()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

}