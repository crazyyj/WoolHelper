package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityManager;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.List;

/**
 * @author newChar
 * date 2021/3/27
 * @since 工具类
 * @since 迭代版本，（以及描述）
 */
public final class Utils {

    @SuppressLint("StaticFieldLeak")
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

    /**
     * 获取手机全局基础信息，包名 App名
     *
     * @param context ctx
     * @return 手机全部App基础信息
     */
    @SuppressLint("QueryPermissionsNeeded")
    public static List<PackageInfo> getDevicesAllAppInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
    }

}
