package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author newChar
 * date 2021/3/27
 * @since 工具类
 * @since 迭代版本，（以及描述）
 */
public final class Utils {

    private static Context sContext;

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

    public static void stat(Context appContext) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
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
        StringWriter memoryWriter = null;
        try {
            try (BufferedReader readerProxy = new BufferedReader(new FileReader(file))) {
                final char[] tempContainer = new char[1024];
                memoryWriter = new StringWriter();
                while (0 < readerProxy.read(tempContainer)) {
                    memoryWriter.write(tempContainer);
                    memoryWriter.flush();
                }
                resultText = memoryWriter.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(memoryWriter);
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

    public static void holdContext(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }

    public static ActivityInfo[] findActivitiesForPackage(
            Context context, String packageName) {
        try {
            final PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return packageInfo.activities;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
