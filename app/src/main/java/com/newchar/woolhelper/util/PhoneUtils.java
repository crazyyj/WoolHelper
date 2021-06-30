package com.newchar.woolhelper.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * @author newChar
 * date 2021/6/16
 * @since 获取手机上App的信息
 * @since 迭代版本，（以及描述）
 */
public class PhoneUtils {


    /**
     * 获取手机全局基础信息，包名 App名
     *
     * @param context ctx
     * @return 手机全部App基础信息
     */
    public static List<PackageInfo> getDevicesAllAppInfo(Context context) {
        return context.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
    }

}
