package com.newchar.accesshelper

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.accessibility.AccessibilityManagerCompat.getEnabledAccessibilityServiceList
import org.jetbrains.annotations.NotNull


/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-11
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class Utils {

    companion object {

        /**
         * 回收Node对象
         */
        fun recycle(nodeInfo: AccessibilityNodeInfo?) {
            nodeInfo?.recycle()
        }

        /**
         * 是否开启服务
         */
        fun isOpenService(@NotNull context: Context): Boolean {
            val manager =
                context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
            var enabledAccessibilityServiceList = manager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC)
            var installedAccessibilityServiceList = manager.installedAccessibilityServiceList
            return manager.isEnabled
        }

//        private boolean checkStealFeature1(String service) {
//            int ok = 0;
//            try {
//                ok = Settings.Secure.getInt(getApplicationContext().getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
//            } catch (Settings.SettingNotFoundException e) {
//            }
//
//            TextUtils.SimpleStringSplitter ms = new TextUtils.SimpleStringSplitter(':');
//            if (ok == 1) {
//                String settingValue = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
//                if (settingValue != null) {
//                    ms.setString(settingValue);
//                    while (ms.hasNext()) {
//                        String accessibilityService = ms.next();
//                        if (accessibilityService.equalsIgnoreCase(service)) {
//                            return true;
//                        }
//
//                    }
//                }

    }

}