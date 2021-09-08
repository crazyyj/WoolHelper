package com.newchar.accesshelper.appinfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.newchar.accesshelper.Utils;
import com.newchar.accesshelper.entry.AppInfo;
import com.newchar.accesshelper.io.AsyncWork;

import java.util.ArrayList;
import java.util.List;

/**
 * @author newChar
 * date 2021/9/5
 * @since 获取全部应用的信息
 * @since 迭代版本，（以及描述）
 */
public class AppInfoManager {

    private static AppInfoManager sAppInfoManager;

    /**
     * 核心数据 应用信息
     */
    private final List<AppInfo> mAppInfoList = new ArrayList<>();

    /**
     * 全部应用数据回调，为权限准备
     */
    private AppInfoCompleteListener mAppInfoCompleteListener;

    private AppInfoManager() {
    }

    public static AppInfoManager get() {
        if (sAppInfoManager == null) {
            synchronized (AppInfoManager.class) {
                if (sAppInfoManager == null) {
                    sAppInfoManager = new AppInfoManager();
                }
            }
        }
        return sAppInfoManager;
    }

    public void preLoad() {
        AsyncWork.getInstance().execute(this::loadAppInfo);
    }

    private void loadAppInfo() {
        final Context context = Utils.getContext();
        final PackageManager packageManager = context.getPackageManager();
        final List<PackageInfo> allAppInfo = Utils.getDevicesAllAppInfo(context);
        AppInfo info;
        for (PackageInfo packageInfo : allAppInfo) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            info = new AppInfo();
            info.packageName = applicationInfo.packageName;
            info.icon = applicationInfo.loadIcon(packageManager);
            info.appName = String.valueOf(applicationInfo.loadLabel(packageManager));
            mAppInfoList.add(info);
        }
        if (mAppInfoCompleteListener != null) {
            mAppInfoCompleteListener.onAppInfoCompleteListener(mAppInfoList);
        }
    }

    public AppInfo searchAppForPackage(String packageName) {
        AppInfo info = null;
        if (!mAppInfoList.isEmpty()) {
            for (AppInfo appInfo : mAppInfoList) {
                if (TextUtils.equals(appInfo.packageName, packageName)) {
                    info = appInfo;
                    break;
                }
            }
        }
        return info;
    }

    public void setAppInfoCompleteListener(AppInfoCompleteListener l) {
        this.mAppInfoCompleteListener = l;
    }

    public List<AppInfo> getAllAppInfo() {
        return mAppInfoList;
    }

    public interface AppInfoCompleteListener {

        void onAppInfoCompleteListener(List<AppInfo> appInfo);

    }

}
