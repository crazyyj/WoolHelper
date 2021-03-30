package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.view.accessibility.AccessibilityManager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * @author newChar
 * date 2021/3/27
 * @since 工具类
 * @since 迭代版本，（以及描述）
 */
public final class Utils {

    private Utils() {
    }

    /**
     * 是否开启服务
     */
    public static boolean isOpenService(Context context) {
        AccessibilityManager manager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        manager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        manager.getInstalledAccessibilityServiceList();
        return manager.isEnabled();
    }

    /**
     * 安全的读文本内容，对File 做基本对校验
     *
     * @param file 需要读的文本
     * @return 文本文件
     */
    public static String safeReadFileToText(File file) {
        return readFileToText(file);
    }

    public static String readFileToText(File file) {
        String resultText = null;
        try {
            try (BufferedReader readerProxy = new BufferedReader(new FileReader(file))) {
                final char[] tempContainer = new char[1024];
                StringWriter memoryWriter = new StringWriter();
                while (0 < readerProxy.read(tempContainer)) {
                    memoryWriter.write(tempContainer);
                    memoryWriter.flush();
                }
                resultText = memoryWriter.toString();
                close(memoryWriter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultText;
    }

    public static void close(Closeable... close) {
        if (null != close && close.length > 0) {
            for (Closeable closeable : close) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
