package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.newchar.accesshelper.compat.ActionInfoCompat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author newChar
 * date 2021/3/29
 * @since 服务的基础类，需要有下一层的业务服务类继承。
 * @since 迭代版本，（以及描述）
 */
public class BaseAccessibilityService extends AccessibilityService {

    private AccessManager accessManager;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Logger.getLogger("onAccessibility").log(Level.WARNING, "onServiceConnected");
        accessManager = new AccessManager(this);
        accessManager.setServiceInfoChangeListener(new AccessManager.ServiceInfoChangeListener() {
            @Override
            public void onServiceInfoChange(AccessibilityServiceInfo info) {
                setServiceInfo(info);
                Log.e("onAccessibility", "onServiceInfoChange " + info.toString());
            }
        });
        accessManager.init();

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e("onAccessibility", "onAccessibilityEvent");
        if (event != null) {
//            把Event传进去，根据包名，类名过滤然后执行后边的具体判断逻辑。要执行哪些操作
            accessManager.onAccessibilityEvent(this, event);
        }
    }

    @Override
    public void onInterrupt() {
        if (accessManager != null) {
            accessManager.release();
            accessManager = null;
        }
    }

}
